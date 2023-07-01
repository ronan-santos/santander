package com.br.santander.application.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.santander.application.business.TransactionService;
import com.br.santander.application.repository.model.external.TransactionQueryResultDTO;
import com.br.santander.application.repository.model.external.TransactionRequestDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts/transactions")
@RequiredArgsConstructor
@Validated
public class TransactionController {

	private final TransactionService service;

	@PostMapping
	public ResponseEntity<Void> processTransaction(@RequestBody @Valid TransactionRequestDTO transaction) {

		service.processTransaction(transaction);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping
	public ResponseEntity<TransactionQueryResultDTO> findPageableByDate(@RequestParam(required = true) LocalDate initialDate,
			@RequestParam(required = true) LocalDate endDate,
			@RequestParam(required = true) int page,
			@RequestParam(defaultValue = "5") int size,
			@RequestParam(required = true) String accountNumber) {

		return ResponseEntity.status(HttpStatus.OK).body(service.findPageableByDate(initialDate, endDate, page, size, accountNumber));

	}
}
