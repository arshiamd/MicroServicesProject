package com.epam.libraryservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.libraryservice.client.BookServiceClient;
import com.epam.libraryservice.dto.BookDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.repository.LibraryRepository;
import com.epam.libraryservice.service.BookService;

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
