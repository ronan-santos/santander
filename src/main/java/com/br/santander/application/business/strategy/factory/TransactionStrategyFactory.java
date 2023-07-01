package com.br.santander.application.business.strategy.factory;

import static com.br.santander.application.infraestruture.Message.STRATEGY_NOT_FOUND;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.santander.application.business.exception.StrategyException;
import com.br.santander.application.business.strategy.transaction.TransactionStrategy;



public class TransactionStrategyFactory<T extends TransactionStrategy>{
	
	private List<T> strategies;

	public TransactionStrategyFactory( @Autowired Set<T> strategiesSet ) {
		
		this.strategies =  strategiesSet.stream().toList();
	}
	
	public TransactionStrategy findStrategy(String trasactionType) {
		
		return strategies
				.stream()
				.filter(strategy -> strategy.getStrategyType().equalsIgnoreCase(trasactionType))
				.findFirst()
				.orElseThrow(() -> new StrategyException(STRATEGY_NOT_FOUND) );
	}
}
