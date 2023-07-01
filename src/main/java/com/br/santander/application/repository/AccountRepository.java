package com.br.santander.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.santander.application.repository.model.internal.Account;
import com.br.santander.application.repository.model.internal.Client;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountNumber(String accountNumber);
	
	Optional<Account> findByClientIdAndTypeId(Long clientId, Long accountTypeId);
	
	List<Account> findByClient(Client client);
	
	
}
