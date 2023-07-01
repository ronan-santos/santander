package com.br.santander.application.repository.model.internal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	private BigDecimal initialValue; 
	
	private BigDecimal calculatedValue;
	
	@ManyToOne
	@JoinColumn(name = "tax_id", referencedColumnName = "id")
	private Tax tax;
	
	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
	
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	private LocalDateTime date;

}
