package com.br.santander.application.business.strategy.transaction;

import org.springframework.stereotype.Service;

import com.br.santander.application.repository.TransactionRepository;
import com.br.santander.application.repository.model.internal.Transaction;
import com.br.santander.application.repository.model.internal.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepositTransactionStrategy implements TransactionStrategy {
	
	private final TransactionRepository repository;

	@Override
	public String getStrategyType() {
		
		return TransactionType.DEPOSIT.name();
	}

	@Override
	public void processTransaction(Transaction transactionBase) {
		
		
		transactionBase.setCalculatedValue(transactionBase.getInitialValue());
		
		repository.save(transactionBase);
		
	}

	

}
