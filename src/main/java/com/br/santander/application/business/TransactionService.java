package com.br.santander.application.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.santander.application.business.strategy.factory.TransactionStrategyFactory;
import com.br.santander.application.business.strategy.transaction.TransactionStrategy;
import com.br.santander.application.repository.TransactionRepository;
import com.br.santander.application.repository.model.external.TransactionDTO;
import com.br.santander.application.repository.model.external.TransactionQueryResultDTO;
import com.br.santander.application.repository.model.external.TransactionRequestDTO;
import com.br.santander.application.repository.model.internal.Transaction;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final AccountService accountService;

	private final TransactionStrategyFactory transactionFactory;

	private final TransactionRepository repository;

	@Transactional
	public void processTransaction(TransactionRequestDTO transactionDto) {

		Transaction transaction = toEntityBase(transactionDto);

		TransactionStrategy strategy = transactionFactory.findStrategy(transaction.getType().name());
		strategy.processTransaction(transaction);
	}

	public TransactionQueryResultDTO findPageableByDate(LocalDate initialDate, LocalDate endDate, int page, int size,
			String accountNumber) {

		Pageable pageRequest = PageRequest.of(page, size);

		Page<Transaction> result = repository.findByAccountAndDateBetween(
				accountService.getAccountByNumber(accountNumber),
				initialDate.atStartOfDay(),
				endDate.atTime(LocalTime.MAX),
				pageRequest);

		return new TransactionQueryResultDTO(result.getTotalElements(), result.getTotalPages(), result.getNumber(),
				result.getContent().stream().map(TransactionDTO::new).toList());

	}

	private Transaction toEntityBase(TransactionRequestDTO transactionDto) {

		return Transaction
				.builder()
					.account(accountService.getAccountByNumber(transactionDto.accountNumber()))
					.initialValue(transactionDto.value())
					.date(LocalDateTime.now())
					.type(transactionDto.type())
				.build();
	}

}
