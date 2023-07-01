package com.br.santander.application.repository.model.external;

import java.util.List;

public record TransactionQueryResultDTO( Long totalItens, int totalPages, int currentPage, List<TransactionDTO> transactions  ) {
	
	

}
