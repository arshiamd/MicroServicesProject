package com.epam.bookservice.service;

import java.util.List;

import com.epam.bookservice.dto.BookDTO;
import com.epam.bookservice.entity.Book;

public interface BookService {

	public String save(BookDTO bookDTO);

	public String update(int id, BookDTO bookDTO);

	public String delete(int id);

	public BookDTO findBookById(int id);

	public List<Book> findAllBooks();
}
