package com.br.santander.application.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.santander.application.repository.model.internal.AccountType;


public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {

}
