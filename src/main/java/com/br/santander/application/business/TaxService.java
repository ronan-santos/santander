package com.br.santander.application.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.br.santander.application.repository.TaxRepository;
import com.br.santander.application.repository.model.internal.IntervalTax;
import com.br.santander.application.repository.model.internal.IntervalType;
import com.br.santander.application.repository.model.internal.Tax;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaxService {

	private final TaxRepository repository;
	
	
	private BiPredicate<IntervalTax, BigDecimal> getFunctionVerifyTaxInterval(){

		return 
			(interval,transactionValue) -> {
				
				Boolean initialIntervalValid = verifyIntervalByTransactionValue(interval.getIntervalValue(), 
								transactionValue, 
								interval.getComparator().getInitialValue());
				
				return Objects.nonNull(interval.getComparator().getFinalValue())
					?  initialIntervalValid
						|| verifyIntervalByTransactionValue(interval.getIntervalValue(), 
									transactionValue,
									interval.getComparator().getFinalValue())
					: initialIntervalValid;
				};

	}
	
	private Boolean verifyIntervalByTransactionValue(BigDecimal intervalValue, 
			BigDecimal transactionValue, int comparator ) {
		
		return transactionValue.compareTo(intervalValue) == comparator;
	}

	public Tax findTaxByTransactionValue(BigDecimal transactionValue ) {
		
		List<Tax> allTax = getAllTaxes();
		
		List<Tax> appliedTaxes = getAppliedTax(getAppliedTax(allTax, IntervalType.INITIAL, transactionValue),
										IntervalType.FINAL, transactionValue);
		
		appliedTaxes.addAll(getAppliedTaxWithoutInterval(allTax, transactionValue));

		return appliedTaxes	.get(0);
	}
	
	private List<Tax> getAppliedTaxWithoutInterval(List<Tax> taxes, BigDecimal transactionValue ){
		
		return taxes
			.stream()
				.filter(tax -> tax.getIntervals().size() == 1 )
				.filter(tax -> getFunctionVerifyTaxInterval().test(tax.getIntervals().get(0), transactionValue) )
				.toList();
		
	}
	
	private List<Tax> getAppliedTax(List<Tax> taxes, IntervalType type, BigDecimal transactionValue ){
		
		return taxes
			.stream()
				.filter(tax -> tax.getIntervals()
						.stream()
							.filter(interval -> interval.getIntervalType() == type)
							.filter(interval -> getFunctionVerifyTaxInterval().test(interval, transactionValue))
							.toList().isEmpty() == Boolean.FALSE)
				.collect(Collectors.toCollection(ArrayList::new));
		
	}
	
	@Cacheable("taxes")
	private List<Tax> getAllTaxes(){
		return repository.findAll();
	}
}
