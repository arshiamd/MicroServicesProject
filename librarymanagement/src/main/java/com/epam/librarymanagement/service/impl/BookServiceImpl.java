package com.epam.librarymanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.librarymanagement.client.BookServiceClient;
import com.epam.librarymanagement.dto.BookDTO;
import com.epam.librarymanagement.entity.Book;
import com.epam.librarymanagement.repository.LibraryRepository;
import com.epam.librarymanagement.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookServiceClient bookServiceClient;

	@Autowired
	LibraryRepository libraryRepository;


	
	@Override
	public ResponseEntity<List<Book>> findAll() {
		return bookServiceClient.findAll();
	}

	
	@Override
	public ResponseEntity<Book> findBookById(int bookId) {
		return bookServiceClient.findBookById(bookId);
	}

	@Override
	public ResponseEntity<String> deleteBook(int bookId) {
		libraryRepository.deleteAll(libraryRepository.findByBookId(bookId));
		return bookServiceClient.deleteBook(bookId);
	}

	@Override
	public ResponseEntity<String> addBook(BookDTO bookDTO) {
		return bookServiceClient.addBook(bookDTO);
	}

	@Override
	public ResponseEntity<String> updateBook(int bookId, BookDTO bookDTO) {
		return bookServiceClient.updateBook(bookId, bookDTO);
	}

}
