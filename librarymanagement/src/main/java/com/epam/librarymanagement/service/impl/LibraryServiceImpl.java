package com.epam.librarymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.librarymanagement.constants.Constants;
import com.epam.librarymanagement.entity.Library;
import com.epam.librarymanagement.exception.DataNotFoundException;
import com.epam.librarymanagement.repository.LibraryRepository;
import com.epam.librarymanagement.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService{
	
	@Autowired
	LibraryRepository libraryRepository;

	@Override
	public String issueBook(String userName, int bookId) {
		String result;
		if(libraryRepository.existsByUserNameAndBookId(userName, bookId)) 
			result = Constants.BOOK_ALREADY_ISSUED;
		else {
			Library library = new Library();
			library.setUserName(userName);
			library.setBookId(bookId);
			libraryRepository.save(library);
			result = Constants.BOOK_ISSUED_TO_USER;
		}
		return result;
	}

	@Override
	public String releaseBook(String userName, int bookId) {
		Library library = libraryRepository.findByUserNameAndBookId(userName, bookId);
		if(library == null)
			throw new DataNotFoundException(Constants.DATA_NOT_FOUND);
		libraryRepository.delete(library);
		return Constants.BOOK_RELEASED;

	}

}
