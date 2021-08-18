package com.epam.librarymanagement.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.librarymanagement.dto.BookDTO;
import com.epam.librarymanagement.entity.Book;

@FeignClient(name = "book-service" , url = "http://localhost:8080")
//@RibbonClient(name = "book-service")
public interface BookServiceClient {

	@PostMapping(value = "/books")
	public ResponseEntity<String> addBook(@RequestBody @Valid BookDTO bookDTO);

	@GetMapping(value = "/books")
	public ResponseEntity<List<Book>> findAll();

	@GetMapping(value = "/books/{bookId}")
	public ResponseEntity<Book> findBookById(@PathVariable int bookId);

	@DeleteMapping(value = "/books/{bookId}")
	public ResponseEntity<String> deleteBook(@PathVariable int bookId);

	@PutMapping(value = "/books/{bookId}")
	public ResponseEntity<String> updateBook(@PathVariable int bookId, @RequestBody @Valid BookDTO bookDTO);

}
