package com.br.santander.application.repository.model.external;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.br.santander.application.infraestruture.Message;
import com.br.santander.application.repository.model.internal.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotEmpty;

public record ClientDTO ( 
		@JsonProperty(access = Access.READ_ONLY) Long id,
		@NotEmpty(message = Message.CONSTRAINT_MESSAGE_NOT_EMPTY_NULL ) String name,
		LocalDate birthDate)  implements Serializable{


	public ClientDTO(Client client) {
		
		this(client.getId(),client.getName(),client.getBirthDate());
	}
	
}
