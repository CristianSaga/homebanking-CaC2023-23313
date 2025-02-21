package com.ar.bankingonline.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ar.bankingonline.domain.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
}
