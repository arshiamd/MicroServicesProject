package com.epam.bookmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.bookmanagement.dto.BookDTO;
import com.epam.bookmanagement.entity.Book;
import com.epam.bookmanagement.exception.BookNotFoundException;
import com.epam.bookmanagement.service.impl.BookServiceImpl;

@RestController
@Validated
public class BookController {

	@Autowired
	BookServiceImpl bookService;

	@PostMapping("books")
	public ResponseEntity<String> addBook(@RequestBody @Valid BookDTO bookDTO) {
		return new ResponseEntity<>(bookService.save(bookDTO), HttpStatus.OK);
	}
	
	@GetMapping("books")
	public ResponseEntity<List<Book>> getBooks() throws BookNotFoundException {
		return new ResponseEntity<>(bookService.findAllBooks(), HttpStatus.OK);
	}

	@GetMapping(value = "/books/{bookId}")
	public ResponseEntity<BookDTO> displayBookById(@PathVariable int bookId) {
		return new ResponseEntity<>(bookService.findBookById(bookId), HttpStatus.OK);
	}

	@PutMapping("books/{bookId}")
	public ResponseEntity<String> updateBook(@PathVariable int bookId,@RequestBody @Valid BookDTO bookDTO){
		return new ResponseEntity<>(bookService.update(bookId,bookDTO), HttpStatus.OK);
	}

	@DeleteMapping("books/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable int bookId){
		return new ResponseEntity<>(bookService.delete(bookId), HttpStatus.OK);
	}
	
	

}
