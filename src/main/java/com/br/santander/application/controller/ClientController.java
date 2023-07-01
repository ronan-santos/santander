package com.br.santander.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.santander.application.business.ClientService;
import com.br.santander.application.repository.model.external.ClientDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

	private final ClientService service;
	
	@PostMapping
	public ResponseEntity<ClientDTO> save(@RequestBody @Valid  ClientDTO client ){
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(service.save(client));
	}
	
	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(){
		
		return ResponseEntity
				.ok(service.findAll());
	}
}
