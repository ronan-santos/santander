package com.br.santander.application.repository.model.internal;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Tax{

	@Id
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	private Double taxValue;
	
	@OneToMany(mappedBy = "tax")
	private List<IntervalTax> intervals;
}
