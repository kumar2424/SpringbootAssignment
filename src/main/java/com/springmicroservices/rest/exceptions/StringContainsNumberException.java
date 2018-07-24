package com.springmicroservices.rest.exceptions;

public class StringContainsNumberException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringContainsNumberException(String exception) {
		super(exception);
	}
}
