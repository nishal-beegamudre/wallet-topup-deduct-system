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
public class TopupResponseDTO {
	
	@JsonProperty("status")
	private Boolean status;
	
	@JsonProperty("new_balance")
	private float newBalance;
	
	@JsonProperty("transaction_id")
	private String transactionId;
	
	@JsonProperty("message")
	private String message;

}
