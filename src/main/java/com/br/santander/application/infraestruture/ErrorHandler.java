package com.br.santander.application.infraestruture;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.santander.application.business.exception.BusinessException;
import com.br.santander.application.business.exception.InsufficientFundsException;
import com.br.santander.application.business.exception.NotFoundException;
import com.br.santander.application.repository.model.external.ResponseError;
import static com.br.santander.application.infraestruture.Message.UNEXPECTED_ERROR;


import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ErrorHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseError> handlerNotFound(NotFoundException e){
		
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage()));
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseError> handlerBusinessException(BusinessException e){
		
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<ResponseError> handlerInsufficientFundsException(InsufficientFundsException e){
		
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseError> handlerInvalidField(MethodArgumentNotValidException e){
		
		log.error(e.getMessage(), e);
		
		String mensage = 
				 e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining(", "));
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseError(HttpStatus.BAD_REQUEST.value(), mensage));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseError> HandlerExceptionNotExpected(Exception e){
		
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), UNEXPECTED_ERROR));
	}

}
