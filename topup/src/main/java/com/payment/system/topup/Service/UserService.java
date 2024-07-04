package com.payment.system.topup.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Entity.User;
import com.payment.system.topup.Repository.UserRepository;
import com.payment.system.topup.Util.Constants;
import com.payment.system.topup.Util.TopupUtil;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private TopupUtil topupUtil;
	

	@Transactional
	@Async
	public CompletableFuture<UserDTO> getUser(long id) {

		UserDTO userDTO = new UserDTO();
		User user = new User();
		user = repository.findById(id).orElse(null);
		
		if(Objects.nonNull(user)) {
			userDTO = topupUtil.userPopulator(user);
		}else {
			userDTO.setMessage(Constants.INVALID_USER_ID);
		}
		
		return CompletableFuture.completedFuture(userDTO);
	}
	
	@Transactional
	@Async
	public CompletableFuture<List<UserDTO>> getAllUsers(){
		List<User> users = new ArrayList<User>();
		users = repository.findAll();
		
		List<UserDTO> userObjects = new ArrayList<UserDTO>();
		users.forEach(user->{
			userObjects.add(topupUtil.userPopulator(user));
			});
		
		return CompletableFuture.completedFuture(userObjects);
	}

}
