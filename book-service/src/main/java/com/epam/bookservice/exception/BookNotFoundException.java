package com.epam.bookservice.exception;

import com.epam.bookservice.constants.Constants;

public class BookNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(int id) {
		super(Constants.BOOK_NOT_FOUND + id);
	}

}
