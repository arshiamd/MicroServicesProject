package com.epam.libraryservice.service;

public interface LibraryService {

	public String issueBook(String userName, int bookId);

	public String releaseBook(String userName, int bookId);

}
