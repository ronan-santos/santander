package com.br.santander.application.repository.model.external;

import java.io.Serializable;

import com.br.santander.application.repository.model.internal.AccountType;

public record AccountTypeDTO( Long id, String name, Boolean isCharged ) implements Serializable {
	
	public AccountTypeDTO(AccountType accountType) {
		
		this(accountType.getId(), accountType.getName(),accountType.getIsCharged());
	}

}
