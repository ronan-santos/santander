package com.br.santander.application.business.exception;

public class InsufficientFundsException extends RuntimeException {

	private static final long serialVersionUID = 502513733944700798L;

	public InsufficientFundsException(String message) {
		
		super(message);
	}

}
