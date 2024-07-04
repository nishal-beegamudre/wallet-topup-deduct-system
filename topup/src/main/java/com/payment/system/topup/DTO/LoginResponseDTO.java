package com.payment.system.topup.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
	
	private String token;

    private long expiresIn;
    
    private String message;

    public String getToken() {
        return token;
    }

}
