package com.br.santander.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.santander.application.repository.model.internal.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {

}
