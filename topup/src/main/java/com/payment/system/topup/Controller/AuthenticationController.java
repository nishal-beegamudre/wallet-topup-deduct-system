package com.payment.system.topup.Controller;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.system.topup.DTO.LoginResponseDTO;
import com.payment.system.topup.DTO.LoginUserDTO;
import com.payment.system.topup.DTO.RegisterUserDTO;
import com.payment.system.topup.DTO.UserDTO;
import com.payment.system.topup.Entity.User;
import com.payment.system.topup.Service.AuthenticationService;
import com.payment.system.topup.Service.JwtService;
import com.payment.system.topup.Util.Constants;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
	
	@Autowired
    private final JwtService jwtService;
    
	@Autowired
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public UserDTO register(@RequestBody RegisterUserDTO registerUserDto) {
    	UserDTO registeredUser = authenticationService.signup(registerUserDto);

        return registeredUser;
    }

    @PostMapping("/login")
    public LoginResponseDTO authenticate(@RequestBody LoginUserDTO loginUserDto) throws InterruptedException, ExecutionException {
    	
    	if(Objects.nonNull(loginUserDto)&&
    			Objects.nonNull(loginUserDto.getEmail())&&
    			Objects.nonNull(loginUserDto.getPassword())) {
    		
    		User authenticatedUser = authenticationService.authenticate(loginUserDto);

            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());
            loginResponse.setMessage(Constants.LOGIN_SUCCESSFUL);

            return loginResponse;
    		
    	}else {
    		
    		LoginResponseDTO loginResponse = new LoginResponseDTO();
            loginResponse.setMessage(Constants.INVALID_REQUEST_BODY);
            
            return loginResponse;
    		
    	}
    	
        
    }
}
