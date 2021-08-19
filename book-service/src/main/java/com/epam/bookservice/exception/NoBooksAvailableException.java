package com.epam.bookservice.exception;

public class NoBooksAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoBooksAvailableException(String message) {
		super(message);
		
	}

	
}
