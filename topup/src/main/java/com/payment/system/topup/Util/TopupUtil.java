package com.payment.system.topup.Util;

import java.util.Objects;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.payment.system.topup.Controller.TransactionController;
import com.payment.system.topup.DTO.TopupRequestDTO;
import com.payment.system.topup.DTO.TransactionDTO;
import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Entity.Transaction;
import com.payment.system.topup.Entity.User;

@Service
public class TopupUtil {
	
	Logger logger = LoggerFactory.getLogger(TopupUtil.class);
	
	public Boolean isRequestParamValid(TopupRequestDTO topupRequest) {

		if(Objects.nonNull(topupRequest)) {
			Boolean isValid = true;
			isValid = ((Objects.nonNull(topupRequest.getUserId()))
					&&(isNumerical(topupRequest.getUserId()))
					&&(Objects.nonNull(topupRequest.getAmount()))
					&&(topupRequest.getAmount()>=(float) 0.0))?true:false;
			return isValid;
			
		}
		
		return false;
		
	}
	
	public Boolean isNumerical(String userId) {
	
		Pattern pattern = Pattern.compile(Constants.REGEX_WHOLE_NUMBER);
		return pattern.matcher(userId).matches();
	}
	
	public UserDTO userPopulator(User user) {
		
		UserDTO userDTO = new UserDTO();
		
		if(Objects.nonNull(user)) {
				
			userDTO.setBalance
			(Objects.nonNull(user.getBalance())?user.getBalance():null);
			userDTO.setCreatedDate
			(Objects.nonNull(user.getCreatedDate())?user.getCreatedDate():null);
			userDTO.setLastModifiedDate
			(Objects.nonNull(user.getLastModifiedDate())?user.getLastModifiedDate():null);
			userDTO.setEmail
			(Objects.nonNull(user.getEmail())?user.getEmail():null);
			userDTO.setId
			(Objects.nonNull(user.getId())?user.getId():null);
			userDTO.setName
			(Objects.nonNull(user.getName())?user.getName():null);
		}
		
		return userDTO;
		
	}
	
	public TransactionDTO transactionPopulator(Transaction transaction) {
		
		TransactionDTO transactionDTO = new TransactionDTO();
		
		if(Objects.nonNull(transaction)) {
			
			transactionDTO.setAmount
			(Objects.nonNull(transaction.getAmount())?transaction.getAmount():null);
			transactionDTO.setId
			(Objects.nonNull(transaction.getId())?transaction.getId():null);
			transactionDTO.setNewBalance
			(Objects.nonNull(transaction.getNewBalance())?transaction.getNewBalance():null);
			transactionDTO.setStatus
			(Objects.nonNull(transaction.getStatus())?transaction.getStatus():null);
			transactionDTO.setTransactionDate
			(Objects.nonNull(transaction.getTransactionDate())?transaction.getTransactionDate():null);
			transactionDTO.setTransactionType
			(Objects.nonNull(transaction.getTransactionType())?transaction.getTransactionType():null);
			transactionDTO.setUserId
			(Objects.nonNull(transaction.getUserId())?transaction.getUserId().getId().toString():null);
			
			logger.info("Transaction "+transaction.getId()+" has been fetched successfully");
			
			return transactionDTO;
			
			
		}else {
			
			transactionDTO.setMessage(Constants.INVALID_TRANSACTION_ID);
			
			logger.warn("Invalid transaction ID");
			
			return transactionDTO;
			
		}
		
	}

}
