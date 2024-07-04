package com.payment.system.topup.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payment.system.topup.DTO.BalanceResponseDTO;
import com.payment.system.topup.DTO.TopupRequestDTO;
import com.payment.system.topup.DTO.TopupResponseDTO;
import com.payment.system.topup.DTO.TransactionDTO;
import com.payment.system.topup.Entity.Transaction;
import com.payment.system.topup.Entity.TransactionType;
import com.payment.system.topup.Entity.User;
import com.payment.system.topup.Repository.TransactionRepository;
import com.payment.system.topup.Repository.UserRepository;
import com.payment.system.topup.Util.Constants;
import com.payment.system.topup.Util.TopupUtil;

@Service
public class TransactionService {
	
	Logger logger = LoggerFactory.getLogger(TransactionService.class);
	
	@Autowired
	public TransactionRepository transactionRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public TopupUtil topupUtil;

	@Transactional
	@Async
	public CompletableFuture<TopupResponseDTO> topup(TopupRequestDTO topupRequest) {
		
		logger.info("TOPUP request has been received for the user ID : "+topupRequest.getUserId()+" with amount : "+topupRequest.getAmount());
		
		return updateWallet(topupRequest,TransactionType.TOPUP);	
		
	}

	@Transactional
	@Async
	public CompletableFuture<TopupResponseDTO> deduct(TopupRequestDTO topupRequest) {
		
		logger.info("DEDUCT request has been received for the user ID : "+topupRequest.getUserId()+" with amount : "+topupRequest.getAmount());
		
		return updateWallet(topupRequest,TransactionType.DEDUCT);
	}

	@Transactional
	@Async
	public CompletableFuture<BalanceResponseDTO> fetchBalance(String userId) {
		
		
		long id = Long.valueOf(userId);
		User user = userRepository.findById(id).orElse(null);
		BalanceResponseDTO responseDTO = new BalanceResponseDTO();
		
		if(Objects.nonNull(user)) {
			responseDTO.setBalance(user.getBalance());
			responseDTO.setMessage(Constants.BALANCE_FETCH_SUCCESS);
		}else {
			responseDTO.setMessage(Constants.INVALID_USER_ID);
			logger.info("Balance fetching has been failed due to invalid user ID "+userId);
		}
		
		
		return CompletableFuture.completedFuture(responseDTO);
	}

	@Async
	public CompletableFuture<TransactionDTO> getTransactionDetails(long id) {
		
		Transaction transaction = transactionRepository.findById(id).orElse(null);
		
		return CompletableFuture.completedFuture(topupUtil.transactionPopulator(transaction));
	}
	
	private CompletableFuture<TopupResponseDTO> updateWallet(TopupRequestDTO topupRequest, TransactionType transactionType) {
		
		long id = Long.valueOf(topupRequest.getUserId());
		User user = userRepository.findById(id).orElse(null);
		TopupResponseDTO responseDTO = new TopupResponseDTO();
		
		if(Objects.nonNull(user)) {
			
			if((transactionType==TransactionType.DEDUCT)&&(user.getBalance()<=topupRequest.getAmount())) {
				responseDTO.setMessage(Constants.NOT_ENOUGH_BALANCE);
				responseDTO.setStatus(false);
				logger.info("DEDUCT request for the user ID: "+topupRequest.getUserId()+" has been rejected due to insufficient balance");
				return CompletableFuture.completedFuture(responseDTO);
				
			}
			
			Transaction transaction = new Transaction();
			transaction.setAmount(topupRequest.getAmount());
			transaction.setStatus(true);
			transaction.setTransactionDate(ZonedDateTime.now(ZoneId.of(Constants.ASIA_KOLKATA_TIMEZONE)));
			transaction.setTransactionType((transactionType==TransactionType.TOPUP) ? 
					TransactionType.TOPUP:TransactionType.DEDUCT);
			transaction.setUserId(user);
			transaction.setNewBalance((transactionType==TransactionType.TOPUP) ? 
					(user.getBalance()+topupRequest.getAmount()):
					(user.getBalance()-topupRequest.getAmount()));
			transactionRepository.save(transaction);
			
			
			user.setBalance((transactionType==TransactionType.TOPUP) ? 
					(user.getBalance()+topupRequest.getAmount()):
					(user.getBalance()-topupRequest.getAmount()));
			user.setLastModifiedDate(ZonedDateTime.now(ZoneId.of(Constants.ASIA_KOLKATA_TIMEZONE)));
			userRepository.save(user);
			
			
			responseDTO.setNewBalance(user.getBalance());
			responseDTO.setStatus(true);
			responseDTO.setTransactionId(transaction.getId().toString());
			responseDTO.setMessage(Constants.TRANSACTION_SUCCESSFUL);
			
			logger.info(transactionType+" request has been completed for the user ID : "+
			topupRequest.getUserId()+" with amount : "+topupRequest.getAmount()+" with the "
					+ "transaction ID : "+transaction.getId());
			
			return CompletableFuture.completedFuture(responseDTO);
			
		}else {
			
			logger.info(transactionType+" request has been rejected as it is an the user ID "
			+topupRequest.getUserId()+" is invalid");
			
			responseDTO.setMessage(Constants.INVALID_USER);
			responseDTO.setStatus(false);
			return CompletableFuture.completedFuture(responseDTO);
			
		}
		
		
		
	}
	

}
