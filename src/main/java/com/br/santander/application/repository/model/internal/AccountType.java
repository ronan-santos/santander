package com.br.santander.application.repository.model.internal;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter 
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AccountType {

	@Id
	private Long id;
	
	private String name;
	
	private Boolean isCharged;
	
	
}
