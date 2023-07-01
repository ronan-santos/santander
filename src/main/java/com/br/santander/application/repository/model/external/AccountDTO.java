package com.br.santander.application.repository.model.external;

import java.io.Serializable;

import com.br.santander.application.repository.model.internal.Account;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;

public record AccountDTO(
		@JsonProperty(access = Access.READ_ONLY) Long id,
		@NotNull(message = "AccountType cannot be null") Long accountTypeId,
		@NotNull(message = "Client cannot be null") Long clientId,
		@JsonProperty(access = Access.READ_ONLY) String accountNumber,
		@JsonProperty(access = Access.READ_ONLY) String balance
		) implements Serializable {
	
	public AccountDTO(Account account) {
		
		this(account.getId(), account.getType().getId(), account.getClient().getId(),account.getAccountNumber(),"");
	}
	
	public static AccountDTO accountWithBalance(Account account, String balance) {
		
		return new AccountDTO(account.getId(), account.getType().getId(),
					account.getClient().getId(),account.getAccountNumber(),balance);
	}

}
