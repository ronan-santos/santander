package com.br.santander.application.repository.model.external;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.br.santander.application.repository.model.internal.Transaction;
import com.br.santander.application.repository.model.internal.TransactionType;

public record TransactionDTO(LocalDateTime date, BigDecimal transactionValue, BigDecimal appliedValue, TransactionType type, Double appliedTax ) {

	
	public TransactionDTO(Transaction transaction ) {
	
		this(transaction.getDate(), transaction.getInitialValue(), 
			transaction.getCalculatedValue(), transaction.getType(),
			Objects.nonNull(transaction.getTax())
				? transaction.getTax().getTaxValue()
				: 0);
	}
}
