package com.payment.system.topup.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.payment.system.topup.DTO.RegisterUserDTO;
import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Entity.User;
import com.payment.system.topup.Service.AuthenticationService;
import com.payment.system.topup.Service.UserService;

public class UserServiceTest {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public AuthenticationService authenticationService;
	
	@Test
	public void createUserEntryTest() {
		
		RegisterUserDTO newUser = new RegisterUserDTO();
		newUser.setName("Test Name");
		newUser.setEmail("test123@gmail.com");
		newUser.setPassword("password123");
		
		UserDTO user = authenticationService.signup(newUser);
		
		assertEquals(newUser.getName(),user.getName());
		
	}
	
	
	@Test
	public void getUserTest() {
		
		RegisterUserDTO newUser = new RegisterUserDTO();
		newUser.setName("Test Name");
		newUser.setEmail("test123@gmail.com");
		newUser.setPassword("password123");
		
		UserDTO user = authenticationService.signup(newUser);
		
		CompletableFuture<UserDTO> future = userService.getUser(user.getId());
		UserDTO outputUser = future.join();
		
		assertEquals(outputUser.getId(),user.getId());
		assertEquals(outputUser.getName(),user.getName());
		
	}
	
	
	@Test
	public void getAllUsers() {
		
		RegisterUserDTO newUser1 = new RegisterUserDTO();
		newUser1.setName("Test Name1");
		newUser1.setEmail("test1@gmail.com");
		newUser1.setPassword("password123");
		
		UserDTO user1 = authenticationService.signup(newUser1);
		
		RegisterUserDTO newUser2 = new RegisterUserDTO();
		newUser2.setName("Test Name2");
		newUser2.setEmail("test2@gmail.com");
		newUser2.setPassword("password123");
		
		UserDTO user2 = authenticationService.signup(newUser2);
		
		CompletableFuture<List<UserDTO>> future = userService.getAllUsers();
		List<UserDTO> outputUsers = future.join();
		
		assertEquals(outputUsers.get(0).getId(),user1.getId());
		assertEquals(outputUsers.get(1).getId(),user2.getId());
		
		
	}

}
