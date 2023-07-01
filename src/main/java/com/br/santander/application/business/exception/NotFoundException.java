package com.br.santander.application.business.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 9192931790146600325L;

	public NotFoundException(String message) {
		
		super(message);
	}

}
