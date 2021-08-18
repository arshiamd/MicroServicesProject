package com.epam.bookmanagement.service;

import java.util.List;

import com.epam.bookmanagement.dto.BookDTO;
import com.epam.bookmanagement.entity.Book;

public interface BookService {

	public String save(BookDTO bookDTO);

	public String update(int id, BookDTO bookDTO);

	public String delete(int id);

	public BookDTO findBookById(int id);

	public List<Book> findAllBooks();
}
