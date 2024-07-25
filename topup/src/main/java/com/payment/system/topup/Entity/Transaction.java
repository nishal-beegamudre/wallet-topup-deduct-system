package com.payment.system.topup.Entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="transaction")
public class Transaction implements Serializable {
	
	@Id
	@GeneratedValue
	@JsonProperty("id")
	private Long id;
	
	@Column(name="amount")
	@JsonProperty("amount")
	private float amount;
	
	@Column(name="status")
	@JsonProperty("status")
	private Boolean status;
	
	@JoinColumn(name="user_id")
	@JsonProperty("user_id")
	@ManyToOne
	private User userId;
	
	@Column(name="new_balance")
	@JsonProperty("new_balance")
	private float newBalance;
	
	@Enumerated(EnumType.STRING)
	@Column(name="transaction_type")
	@JsonProperty("transaction_type")
	private TransactionType transactionType;
	
	@Column(name="transaction_date")
	@JsonProperty("transaction_date")
	@TimeZoneStorage(TimeZoneStorageType.NATIVE)
	private ZonedDateTime transactionDate;

}
