package com.payment.system.topup.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.system.topup.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User> findByEmail(String email);

}
