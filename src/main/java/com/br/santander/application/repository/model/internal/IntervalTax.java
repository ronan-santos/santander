package com.br.santander.application.repository.model.internal;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class IntervalTax {

	@Id
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	private BigDecimal intervalValue;
	
	@ManyToOne
	@JoinColumn(name = "tax_id", referencedColumnName = "id")
	private Tax tax;
	
	@Enumerated(EnumType.STRING)
	private IntervalType intervalType;

	
	@ManyToOne
	@JoinColumn(name = "comparator_id", referencedColumnName = "id")
	private Comparator comparator; 
	
	
	
}
