package com.br.santander.application.repository.model.external;

import java.io.Serializable;
import java.math.BigDecimal;

import static com.br.santander.application.infraestruture.Message.CONSTRAINT_MESSAGE_NOT_EMPTY_NULL;
import com.br.santander.application.repository.model.internal.TransactionType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TransactionRequestDTO( @NotEmpty(message = CONSTRAINT_MESSAGE_NOT_EMPTY_NULL) String accountNumber,
							@NotNull(message = CONSTRAINT_MESSAGE_NOT_EMPTY_NULL) BigDecimal value,
							@NotNull(message = CONSTRAINT_MESSAGE_NOT_EMPTY_NULL) TransactionType type  ) implements Serializable {

}
