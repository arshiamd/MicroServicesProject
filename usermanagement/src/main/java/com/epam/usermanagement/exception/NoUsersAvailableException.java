package com.epam.usermanagement.exception;

public class NoUsersAvailableException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoUsersAvailableException(String message) {
		super(message);
	}

}
