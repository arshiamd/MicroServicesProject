package com.epam.bookservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.bookservice.entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer>{
	
}
