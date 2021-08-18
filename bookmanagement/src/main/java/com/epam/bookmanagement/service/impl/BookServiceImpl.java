package com.epam.bookmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.bookmanagement.constants.Constants;
import com.epam.bookmanagement.dto.BookDTO;
import com.epam.bookmanagement.entity.Book;
import com.epam.bookmanagement.repository.BookRepository;
import com.epam.bookmanagement.service.BookService;
import com.epam.bookmanagement.exception.BookNotFoundException;
import com.epam.bookmanagement.exception.NoBooksAvailableException;

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
	public String update(int id, BookDTO bookDTO) {
		Book book = mapper.map(bookDTO, Book.class);
		if (!bookRepository.existsById(id))
			throw new BookNotFoundException(Constants.BOOK_NOT_FOUND);
		bookRepository.save(book);
		return Constants.BOOK_UPDATED;

	}

	@Override
	public String delete(int bookId) {
		Optional<Book> book = bookRepository.findById(bookId);
		if (!book.isPresent())
			throw new BookNotFoundException(Constants.BOOK_NOT_FOUND);
		bookRepository.deleteById(bookId);
		return Constants.BOOK_DELETED;
	}

	@Override
	public BookDTO findBookById(int id) {
		Optional<Book> book = bookRepository.findById(id);
		if (!book.isPresent())
			throw new BookNotFoundException(Constants.BOOK_NOT_FOUND);
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
