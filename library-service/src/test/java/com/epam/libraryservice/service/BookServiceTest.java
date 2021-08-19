package com.epam.libraryservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.libraryservice.client.BookServiceClient;
import com.epam.libraryservice.dto.BookDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.repository.LibraryRepository;
import com.epam.libraryservice.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	BookServiceImpl bookService;

	@Mock
	LibraryRepository libraryRepository;

	@Mock
	BookServiceClient bookServiceClient;

	private Book book;
	private Library library;
	private BookDTO bookDto;

	@BeforeEach
	void setup() {
		book = new Book();
		book.setBookId(23);
		book.setBookName("Spring Essentials");
		book.setBookPublisher("Sun Micro");
		book.setBookAuthor("James Gosling");

		library = new Library();
		library.setId(1);
		library.setBookId(23);
		library.setUserName("arshia2313");

		bookDto = new BookDTO();
		bookDto.setBookId(23);
		bookDto.setBookName("Spring Essentials");
		bookDto.setBookPublisher("Sun Micro");
		bookDto.setBookAuthor("James Gosling");
	}

	@Test
	void testAddBook() {
		when(bookServiceClient.addBook(bookDto)).thenReturn(new ResponseEntity<>("Book Added!", HttpStatus.OK));
		assertEquals("Book Added!", bookService.addBook(bookDto).getBody());
	}

	@Test
	void testDisplayBookById() {
		when(bookServiceClient.findBookById(23)).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
		assertEquals(book, bookService.findBookById(23).getBody());
	}

	@Test
	void testDisplayAllBooks() {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookServiceClient.findAll()).thenReturn(new ResponseEntity<>(books, HttpStatus.OK));
		assertEquals(book, bookService.findAll().getBody().get(0));
	}

	@Test
	void testUpdateBook() {
		when(bookServiceClient.updateBook(bookDto.getBookId(), bookDto))
				.thenReturn(new ResponseEntity<>("Book Updated!", HttpStatus.OK));
		assertEquals("Book Updated!", bookService.updateBook(bookDto.getBookId(), bookDto).getBody());
	}

	@Test
	void testDeleteBook() {
		List<Library> record = new ArrayList<>();
		record.add(library);
		when(libraryRepository.findByBookId(23)).thenReturn(record);
		doNothing().when(libraryRepository).deleteAll(record);
		when(bookServiceClient.deleteBook(23)).thenReturn(new ResponseEntity<>("Book Deleted!", HttpStatus.OK));
		assertEquals("Book Deleted!", bookService.deleteBook(23).getBody());

	}

}
