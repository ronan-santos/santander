package com.br.santander.application.business.strategy.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.br.santander.application.business.AccountService;
import com.br.santander.application.business.TaxService;
import com.br.santander.application.business.exception.InsufficientFundsException;
import static com.br.santander.application.infraestruture.Message.INSUFFICIENT_FUNDS;
import com.br.santander.application.repository.TransactionRepository;
import com.br.santander.application.repository.model.internal.Tax;
import com.br.santander.application.repository.model.internal.Transaction;
import com.br.santander.application.repository.model.internal.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DebitTransactionStrategy implements TransactionStrategy {

	private final TransactionRepository repository;
	private final TaxService taxService;
	private final AccountService accountService;

	@Override
	public String getStrategyType() {
		return TransactionType.DEBIT.name();
	}

	@Override
	public void processTransaction(Transaction transactionBase) {
		
		validateBalance(transactionBase);
		
		if(Boolean.TRUE.equals(transactionBase.getAccount().getType().getIsCharged()))
			processTransactionCharged(transactionBase);
		else
			processTransactionNotCharged(transactionBase);
	

	}
	
	private void validateBalance(Transaction transactionBase) {
		
		if(accountService.calculateAccountBalance(transactionBase.getAccount())
				.compareTo(transactionBase.getInitialValue()) < 0)
			throw new InsufficientFundsException(INSUFFICIENT_FUNDS);
		
	}
	
	private void processTransactionNotCharged(Transaction transactionBase) {
		
		transactionBase.setCalculatedValue(transactionBase.getInitialValue());
		repository.save(transactionBase);
	}
	
	private void  processTransactionCharged(Transaction transactionBase) {
		
		transactionBase.setTax(taxService.findTaxByTransactionValue(transactionBase.getInitialValue()));
		transactionBase.setCalculatedValue(
				applyTaxInTransactionValue(transactionBase.getTax(), transactionBase.getInitialValue()));
		repository.save(transactionBase);
	}

	private BigDecimal applyTaxInTransactionValue(Tax tax, BigDecimal transactionValue) {

		BigDecimal appliedTax = BigDecimal.valueOf(tax.getTaxValue()).divide(BigDecimal.valueOf(100));
		return transactionValue
				.subtract((transactionValue.multiply(appliedTax).setScale(2, RoundingMode.HALF_EVEN)));

	}

}
