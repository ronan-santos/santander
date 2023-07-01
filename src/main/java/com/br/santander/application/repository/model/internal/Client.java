package com.br.santander.application.repository.model.internal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.br.santander.application.repository.model.external.ClientDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Client {

	@Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	private LocalDate birthDate;
	
	public static Client toClient(ClientDTO clientDto) {
		
		return Client
				.builder()
					.name(clientDto.name())
					.birthDate(clientDto.birthDate())
				.build();
	}
}
