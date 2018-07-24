package com.springmicroservices.rest.exceptions;

public class NotAValidInputException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAValidInputException(String exception) {
		super(exception);
	}
}
