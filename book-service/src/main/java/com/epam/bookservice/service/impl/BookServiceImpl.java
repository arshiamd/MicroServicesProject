package com.epam.bookservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bookservice.constants.Constants;
import com.epam.bookservice.dto.BookDTO;
import com.epam.bookservice.entity.Book;
import com.epam.bookservice.exception.BookNotFoundException;
import com.epam.bookservice.exception.NoBooksAvailableException;
import com.epam.bookservice.repository.BookRepository;
import com.epam.bookservice.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public String save(BookDTO bookDTO) {
		Book book = mapper.map(bookDTO, Book.class);
		String result;
		if (bookRepository.existsById(book.getBookId()))
			result = Constants.BOOK_ALREADY_EXISTS;
		else {
			bookRepository.save(book);
			result = Constants.BOOK_ADDED;
		}
		return result;

	}

	@Override
	public String update(int bookId, BookDTO bookDTO) {
		Book book = mapper.map(bookDTO, Book.class);
		if (!bookRepository.existsById(bookId))
			throw new BookNotFoundException(bookId);
		bookRepository.save(book);
		return Constants.BOOK_UPDATED;

	}

	@Override
	public String delete(int bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (!book.isPresent())
			throw new BookNotFoundException(bookId);
		bookRepository.deleteById(bookId);
		return Constants.BOOK_DELETED;
	}

	@Override
	public BookDTO findBookById(int bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (!book.isPresent())
			throw new BookNotFoundException(bookId);
		return mapper.map(book.get(), BookDTO.class);
	}

	@Override
	public List<Book> findAllBooks(){
		List<Book> books = (List<Book>) bookRepository.findAll();
		if(books.isEmpty())
			throw new NoBooksAvailableException(Constants.BOOKS_UNAVAILABLE);
		return books;
	}

}
