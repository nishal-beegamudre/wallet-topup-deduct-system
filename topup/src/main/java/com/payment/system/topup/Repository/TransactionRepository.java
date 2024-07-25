package com.payment.system.topup.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.system.topup.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	

}
