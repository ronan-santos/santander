package com.br.santander.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.santander.application.repository.model.internal.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
	

}
