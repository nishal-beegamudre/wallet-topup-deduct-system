package com.payment.system.topup.DTO;

import java.time.ZonedDateTime;

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
public class UserDTO {
	
	@JsonProperty("user_id")
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("balance")
	private float balance;
	
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
	
	@JsonProperty("createdDate")
	private ZonedDateTime createdDate;
	
	@JsonProperty("lastModifiedDate")
	private ZonedDateTime lastModifiedDate;
	
	@JsonProperty("message")
	private String message;

}
