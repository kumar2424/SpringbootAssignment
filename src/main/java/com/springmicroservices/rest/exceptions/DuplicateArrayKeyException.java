package com.springmicroservices.rest.exceptions;

public class DuplicateArrayKeyException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateArrayKeyException(String exception) {
		super(exception);
	}
}
