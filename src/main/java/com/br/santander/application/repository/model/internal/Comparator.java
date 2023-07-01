package com.br.santander.application.repository.model.internal;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Comparator {

	@Id
	private Long id;
	
	private String comparatorValue;
	
	private Integer initialValue;
	
	private Integer finalValue;
	
	
}
