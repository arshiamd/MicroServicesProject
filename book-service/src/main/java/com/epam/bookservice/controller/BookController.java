package com.epam.bookservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.core.env.Environment;
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

import com.epam.bookservice.dto.BookDTO;
import com.epam.bookservice.entity.Book;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.service.impl.BookServiceImpl;

@RestController
@Validated
public class BookController {

	@Autowired
	BookServiceImpl bookService;
	
	@Autowired
	Environment environment;

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
		BookDTO bookDTO = bookService.findBookById(bookId);
		bookDTO.setPort( Integer.parseInt(environment.getProperty("local.server.port")));
		System.out.println(environment.getProperty("local.server.port"));
		return new ResponseEntity<>(bookDTO, HttpStatus.OK);
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
