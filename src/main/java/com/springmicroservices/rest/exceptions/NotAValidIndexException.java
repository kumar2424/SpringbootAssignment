package com.springmicroservices.rest.exceptions;

public class NotAValidIndexException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotAValidIndexException(String exception) {
		super(exception);
	}
}
