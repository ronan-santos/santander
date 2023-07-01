package com.br.santander.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.santander.application.business.AccountService;
import com.br.santander.application.repository.model.external.AccountDTO;
import com.br.santander.application.repository.model.external.AccountTypeDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Validated
public class AccountCrontroller {
	
	private final AccountService service;
	
	@PostMapping
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody @Valid AccountDTO account ){
		
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.save(account));
	}
	
	@GetMapping("/{accountNumber}")
	public ResponseEntity<AccountDTO> getAccountByNumber(@PathVariable String accountNumber ){
		
		return ResponseEntity
				.ok(service.getAccountDtoByNumber(accountNumber));
	}
	
	@GetMapping("/client/{clientId}")
	public ResponseEntity<List<AccountDTO>> getAccountByClient(@PathVariable Long clientId ){
		
		return ResponseEntity
				.ok(service.getByClientId(clientId));
	}
	
	@GetMapping("/types")
	public ResponseEntity<List<AccountTypeDTO>> listAccountsType(){
		
		
		return ResponseEntity.ok(service.getAllAccountsType());
	}

}
