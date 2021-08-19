package com.epam.libraryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.libraryservice.service.LibraryService;

@RestController
public class LibraryController {
	
	@Autowired
	LibraryService libraryService;
	
	@PostMapping("/library/users/{userName}/books/{bookId}")
	public ResponseEntity<String> issueBook(@PathVariable String userName, @PathVariable int bookId){
		return new ResponseEntity<>(libraryService.issueBook(userName, bookId),HttpStatus.OK);
	}
	
	@DeleteMapping("/library/users/{userName}/books/{bookId}")
	public ResponseEntity<String> releaseBook(@PathVariable String userName, @PathVariable int bookId){
		return new ResponseEntity<>(libraryService.releaseBook(userName, bookId),HttpStatus.OK);
	}

}
