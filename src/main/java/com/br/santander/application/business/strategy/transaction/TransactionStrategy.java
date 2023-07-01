package com.br.santander.application.business.strategy.transaction;

import com.br.santander.application.repository.model.internal.Transaction;

public interface TransactionStrategy {

	String getStrategyType();
	
	void processTransaction( Transaction transactionBase );
}
