package com.springmicroservices.rest.exceptions;

public class EmptyStringException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmptyStringException(String exception) {
		super(exception);
	}
}
