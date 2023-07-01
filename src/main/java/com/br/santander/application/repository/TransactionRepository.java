package com.br.santander.application.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.santander.application.repository.model.internal.Account;
import com.br.santander.application.repository.model.internal.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	Page<Transaction> findByAccountAndDateBetween(Account account, LocalDateTime start, LocalDateTime end, Pageable pageable );
	
	List<Transaction> findByAccountId(Long account);
}
