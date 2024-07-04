package com.payment.system.topup.DTO;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.payment.system.topup.Entity.TransactionType;

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
public class TransactionDTO {
	
	@JsonProperty("transaction_id")
	private Long id;
	
	@JsonProperty("amount")
	private float amount;
	
	@JsonProperty("status")
	private Boolean status;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("new_balance")
	private float newBalance;
	
	@JsonProperty("transaction_type")
	private TransactionType transactionType;
	
	@JsonProperty("transaction_date")
	private ZonedDateTime transactionDate;
	
	@JsonProperty("message")
	private String message;

}
