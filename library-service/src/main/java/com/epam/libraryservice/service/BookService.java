package com.epam.libraryservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.libraryservice.dto.BookDTO;
import com.epam.libraryservice.entity.Book;

public interface BookService {

	public ResponseEntity<List<Book>> findAll();

	public ResponseEntity<Book> findBookById(int bookId);
	
	public ResponseEntity<String> deleteBook(int bookId);
	
	public ResponseEntity<String> addBook(BookDTO bookDTO);
	
	public ResponseEntity<String> updateBook(int bookId, BookDTO bookDTO);

}
