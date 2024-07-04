package com.payment.system.topup.Test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.payment.system.topup.DTO.BalanceResponseDTO;
import com.payment.system.topup.DTO.RegisterUserDTO;
import com.payment.system.topup.DTO.TopupRequestDTO;
import com.payment.system.topup.DTO.TopupResponseDTO;
import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Entity.Transaction;
import com.payment.system.topup.Entity.TransactionType;
import com.payment.system.topup.Repository.TransactionRepository;
import com.payment.system.topup.Service.AuthenticationService;
import com.payment.system.topup.Service.TransactionService;
import com.payment.system.topup.Service.UserService;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DataJpaTest
public class TransactionServiceTest {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AuthenticationService authenticationService;
	
	
	@Test
	@Transactional
	public void topupTest() {
		
		RegisterUserDTO newUser = new RegisterUserDTO();
		newUser.setName("Test Name");
		newUser.setEmail("test123@gmail.com");
		newUser.setPassword("password123");
		
		UserDTO user = authenticationService.signup(newUser);
		
		TopupRequestDTO request = new TopupRequestDTO();
		request.setUserId(user.getId().toString());
		request.setAmount((float) 125.0);

		TopupResponseDTO response = transactionService.topup(request).join();
		Transaction transaction = transactionRepository.findById
				(Long.valueOf(response.getTransactionId())).orElse(null);
		
		assertEquals(true,response.getStatus());
		assertEquals(transaction.getId().toString(),response.getTransactionId());
		assertEquals(transaction.getTransactionType(),TransactionType.TOPUP);
		assertEquals(transaction.getUserId(),user.getId());
		assertEquals(transaction.getStatus(),true);
		
		
	}
	
	@Test
	public void topupToInvalidUserTest() {
		
		TopupRequestDTO request = new TopupRequestDTO();
		request.setUserId("123");
		request.setAmount((float) 125.0);

			CompletableFuture<TopupResponseDTO> future = transactionService.topup(request);
			TopupResponseDTO response = future.join();
			assertEquals(false,response.getStatus());

	
	}
	
	
	@Test
	public void deductTest() {
		
		RegisterUserDTO newUser = new RegisterUserDTO();
		newUser.setName("Test Name");
		newUser.setEmail("test123@gmail.com");
		newUser.setPassword("password123");
		
		UserDTO user = authenticationService.signup(newUser);
		
		TopupRequestDTO request = new TopupRequestDTO();
		request.setUserId(user.getId().toString());
		request.setAmount((float) 125.0);
		
		CompletableFuture<TopupResponseDTO> futureTopup = transactionService.topup(request);
		CompletableFuture<TopupResponseDTO> futureDeduct = transactionService.deduct(request);
		
		TopupResponseDTO topupResponse = futureTopup.join();
		TopupResponseDTO deductResponse = futureDeduct.join();
		
		Transaction transaction = transactionRepository.findById
				(Long.valueOf(deductResponse.getTransactionId())).orElse(null);
		
		assertEquals(true,deductResponse.getStatus());
		assertEquals(transaction.getId().toString(),deductResponse.getTransactionId());
		assertEquals(transaction.getTransactionType(),TransactionType.DEDUCT);
		assertEquals(transaction.getUserId(),user.getId());
		assertEquals(transaction.getStatus(),true);
		
	}
	
	@Test
	public void deductGreaterAmountThanBalanceTest() {
		
		RegisterUserDTO newUser = new RegisterUserDTO();
		newUser.setName("Test Name");
		newUser.setEmail("test123@gmail.com");
		newUser.setPassword("password123");
		
		UserDTO user = authenticationService.signup(newUser);
		
		TopupRequestDTO request = new TopupRequestDTO();
		request.setUserId(user.getId().toString());
		request.setAmount((float) 125.0);
		
		CompletableFuture<TopupResponseDTO> topupFuture = transactionService.topup(request);
		CompletableFuture<TopupResponseDTO> deductFuture = transactionService.deduct(request);
		
		TopupResponseDTO topupResponse = topupFuture.join();
		request.setAmount((float) 126.0);
		TopupResponseDTO deductResponse = deductFuture.join();
		
		Transaction transaction = transactionRepository.findById
				(Long.valueOf(deductResponse.getTransactionId())).orElse(null);
		
		assertEquals(false,deductResponse.getStatus());
		
	}
	
	
	@Test
	public void balanceTest() {
		
		RegisterUserDTO newUser = new RegisterUserDTO();
		newUser.setName("Test Name");
		newUser.setEmail("test123@gmail.com");
		newUser.setPassword("password123");
		
		UserDTO user = authenticationService.signup(newUser);
		
		TopupRequestDTO request = new TopupRequestDTO();
		request.setUserId(user.getId().toString());
		request.setAmount((float) 125.0);
		
		CompletableFuture<TopupResponseDTO> topupFuture = transactionService.topup(request);
		CompletableFuture<BalanceResponseDTO> balanceFuture = transactionService.fetchBalance(user.getId().toString());
		
		TopupResponseDTO topupResponse = topupFuture.join();
		BalanceResponseDTO balanceResponse = balanceFuture.join();
		
		assertEquals(request.getAmount(),balanceResponse.getBalance());
		
		
	}
	

}
