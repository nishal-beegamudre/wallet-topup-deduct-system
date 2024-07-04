package com.payment.system.topup.Service;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payment.system.topup.DTO.LoginUserDTO;
import com.payment.system.topup.DTO.RegisterUserDTO;
import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Entity.User;
import com.payment.system.topup.Repository.UserRepository;
import com.payment.system.topup.Util.Constants;
import com.payment.system.topup.Util.TopupUtil;

@Service
public class AuthenticationService {
	
	@Autowired
    private final UserRepository userRepository;
    
	@Autowired
    private final PasswordEncoder passwordEncoder;
    
	@Autowired
    private final AuthenticationManager authenticationManager;
	
	@Autowired
	private final TopupUtil topupUtil;

    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        TopupUtil topupUtil
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.topupUtil = topupUtil;
    }

    
    public UserDTO signup(RegisterUserDTO input) {
    	
    	if(Objects.nonNull(input)&&
    			Objects.nonNull(input.getEmail())&&
    			Objects.nonNull(input.getName())&&
    			Objects.nonNull(input.getPassword())&&
    			(input.getEmail().contains(".com"))&&
    			(input.getEmail().contains("@"))) {
    		
    		User existingUser = userRepository.findByEmail(input.getEmail()).orElse(null);
        	
        	if(existingUser==null) {
        		
        		User user = new User();
                user.setName(input.getName());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));
        		user.setCreatedDate(ZonedDateTime.now(ZoneId.of(Constants.ASIA_KOLKATA_TIMEZONE)));
        		user.setLastModifiedDate(ZonedDateTime.now(ZoneId.of(Constants.ASIA_KOLKATA_TIMEZONE)));
                user.setBalance((float) 0.0);

                userRepository.save(user);
                
                return topupUtil.userPopulator(user);
        		
        	}
        	else {
        		
        		UserDTO userDTO = new UserDTO();
        		userDTO.setMessage(Constants.USER_EXISTS_ALREADY);
        		
        		return userDTO;
        	}
    		
    		
    		
    	}else {
    		
    		UserDTO userDTO = new UserDTO();
    		userDTO.setMessage(Constants.INVALID_REQUEST_BODY);
    		
    		return userDTO;
    		
    	}
    	
    	
    	
        
    }

    
    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}