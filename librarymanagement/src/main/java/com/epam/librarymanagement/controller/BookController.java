package com.epam.librarymanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.librarymanagement.dto.BookDTO;
import com.epam.librarymanagement.entity.Book;
import com.epam.librarymanagement.service.impl.BookServiceImpl;

@RestController
public class BookController {
	
	
	@Autowired
	BookServiceImpl bookService;

	@GetMapping("/library/books/{bookId}")
	public ResponseEntity<Book> displayBookWithGivenId(@PathVariable int bookId) {
		return bookService.findBookById(bookId);
	}

	@GetMapping("/library/books")
	public ResponseEntity<List<Book>> displayBooks() {
		return bookService.findAll();
	}

	@DeleteMapping("/library/books/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
		return bookService.deleteBook(bookId);
	}
	
	@PostMapping("/library/books")
	public ResponseEntity<String> addBook(@RequestBody @Valid BookDTO bookDTO){
		return bookService.addBook(bookDTO);
	}
	
	@PutMapping("/library/books/{bookId}")
	public ResponseEntity<String> updateBook(@PathVariable int bookId,@RequestBody @Valid BookDTO bookDTO){
		return bookService.updateBook(bookId,bookDTO);
	}

}
