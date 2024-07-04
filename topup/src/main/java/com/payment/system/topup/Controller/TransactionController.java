package com.payment.system.topup.Controller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.system.topup.DTO.BalanceResponseDTO;
import com.payment.system.topup.DTO.TopupRequestDTO;
import com.payment.system.topup.DTO.TopupResponseDTO;
import com.payment.system.topup.DTO.TransactionDTO;
import com.payment.system.topup.Service.TransactionService;
import com.payment.system.topup.Util.Constants;
import com.payment.system.topup.Util.TopupUtil;

@RestController
public class TransactionController {
	
	Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	public TransactionService transactionService;
	
	@Autowired
	public TopupUtil util;
	
	@PostMapping("/topup")
	@CachePut(value="topupCache")
	public TopupResponseDTO topup(@RequestBody TopupRequestDTO topupRequest){
		
		return util.isRequestParamValid(topupRequest)?
				transactionService.topup(topupRequest).thenApplyAsync(result->result).join():
				new TopupResponseDTO(false,(float) 0.0,null,Constants.INVALID_REQUEST_BODY);
		
	}

	@PostMapping("/deduct")
	@CachePut(value="deductCache")
	public TopupResponseDTO deduct(@RequestBody TopupRequestDTO topupRequest){
		
		return util.isRequestParamValid(topupRequest)?
				transactionService.deduct(topupRequest).thenApplyAsync(result->result).join():
				new TopupResponseDTO(false,(float) 0.0,null,Constants.INVALID_REQUEST_BODY);
		
	}
	
	@GetMapping("/balance")
	@Cacheable(value="balanceAmountCache")
	public BalanceResponseDTO balanceAmount(@RequestParam("user_id") String userId){
		
		return (Objects.nonNull(userId)&&(util.isNumerical(userId)))?
				transactionService.fetchBalance(userId).thenApplyAsync(result->result).join():
				new BalanceResponseDTO((float) 0.0,Constants.INVALID_REQUEST_BODY);
		
		
		
	}
	
	@GetMapping("/getTransactionDetails/{id}")
	@Cacheable(value="getTransactionDetailsCache")
	public TransactionDTO getTransactionDetails(@PathVariable long id){
		
		TransactionDTO transactionDTO = new TransactionDTO();
		transactionDTO.setMessage(Constants.INVALID_TRANSACTION_ID);
		
		return Objects.nonNull(id)?
				transactionService.getTransactionDetails(id).thenApplyAsync(result->result).join():
				transactionDTO;
		
	}
	
	
}

