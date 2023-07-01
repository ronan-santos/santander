package com.br.santander.application.business.exception;

public class BusinessException extends RuntimeException {
	

	private static final long serialVersionUID = 3808016339090303310L;

	public BusinessException(String message) {
		
		super(message);
	}

}
