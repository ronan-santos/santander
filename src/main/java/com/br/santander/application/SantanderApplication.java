package com.br.santander.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.br.santander.application.business.strategy.factory.TransactionStrategyFactory;
import com.br.santander.application.business.strategy.transaction.TransactionStrategy;

@SpringBootApplication
@EnableCaching
public class SantanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SantanderApplication.class, args);
	}
	
	@Bean
	TransactionStrategyFactory transactionStrategyFactory(List<TransactionStrategy> strategies ) {
		
		return new TransactionStrategyFactory(strategies.stream().collect(Collectors.toSet()));
	}

}
