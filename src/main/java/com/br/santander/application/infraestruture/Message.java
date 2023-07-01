package com.br.santander.application.infraestruture;

public class Message {

	private Message() {}
	
	public static final String CONSTRAINT_MESSAGE_NOT_EMPTY_NULL = "Cannot be empty or null";
	public static final String CLIENT_NOT_FOUND = "Client not found";
	public static final String ACCOUNT_TYPE_NOT_FOUND = "AccountType not found";
	public static final String ACCOUNT_NUMBER_EXISTS = "Alredy exists account with this number";
	public static final String UNEXPECTED_ERROR = "Try again later";
	public static final String ACCOUNT_NOT_FOUND = "Account not found";
	public static final String ACCOUNT_EXISTS = "Account alredy exists";
	public static final String STRATEGY_NOT_FOUND = "strategy not found";
	public static final String INSUFFICIENT_FUNDS = "Insufficient funds";
}
