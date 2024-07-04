package com.payment.system.topup.Controller;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getUser/{id}")
	@ResponseBody
	@Cacheable(value="getUserCache")
	public UserDTO getUser(@PathVariable long id){
		
		return (Objects.nonNull(id))?userService.getUser(id).thenApplyAsync(result->result).join():null;
		
	}
	
	@GetMapping("getAllUsers")
	@ResponseBody
	@Cacheable(value="getAllUsersCache")
	public List<UserDTO> getAllUsers(){
		
		return userService.getAllUsers().thenApplyAsync(result->result).join();
		
	}
	
	

}
