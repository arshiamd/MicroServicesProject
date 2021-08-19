package com.epam.libraryservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.libraryservice.client.BookServiceClient;
import com.epam.libraryservice.client.UserServiceClient;
import com.epam.libraryservice.constants.Constants;
import com.epam.libraryservice.dto.UserDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.entity.User;
import com.epam.libraryservice.exception.DataNotFoundException;
import com.epam.libraryservice.repository.LibraryRepository;
import com.epam.libraryservice.service.impl.BookServiceImpl;
import com.epam.libraryservice.service.impl.LibraryServiceImpl;
import com.epam.libraryservice.service.impl.UserServiceImpl;

@SpringBootTest
public class LibraryServiceTest {

	@MockBean
	UserServiceImpl userService;

	@MockBean
	BookServiceImpl bookService;

	@Autowired
	LibraryServiceImpl libraryService;

	@MockBean
	LibraryRepository libraryRepository;

	@MockBean
	UserServiceClient userServiceClient;

	@MockBean
	BookServiceClient bookServiceClient;

	private UserDTO userDto;
	private User user;
	private Library library;
	private Book book;

	@BeforeEach
	void setup() {

		userDto = new UserDTO();
		userDto.setUserName("arshia2313");
		userDto.setEmail("arshiamd.777@gmail.com");
		userDto.setName("Arshia Mohammad");

		user = new User();
		user.setUserName("arshia2313");
		user.setEmail("arshiamd.777@gmail.com");
		user.setName("Arshia Mohammad");

		library = new Library();
		library.setId(1);
		library.setBookId(23);
		library.setUserName("arshia2313");

		book = new Book();
		book.setBookId(23);
		book.setBookName("Spring Essentials");
		book.setBookPublisher("Sun Micro");
		book.setBookAuthor("James Gosling");

	}

	@Test
	void testIssueBook() {
		when(userService.findUserById("arshia2313")).thenReturn(user);
		when(bookService.findBookById(23)).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
		when(libraryRepository.existsByUserNameAndBookId("arshia2313", 23)).thenReturn(false);
		when(libraryRepository.save(library)).thenReturn(library);
		assertEquals(Constants.BOOK_ISSUED_TO_USER, libraryService.issueBook("arshia2313", 23));
		testBookAlreadyIssued();

	}

	@Test
	void testBookAlreadyIssued() {
		when(userService.findUserById("arshia2313")).thenReturn(user);
		when(bookService.findBookById(23)).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
		when(libraryRepository.existsByUserNameAndBookId("arshia2313", 23)).thenReturn(true);
		assertEquals(Constants.BOOK_ALREADY_ISSUED, libraryService.issueBook("arshia2313", 23));
	}

	@Test
	void testReleaseBook() {
		when(libraryRepository.findByUserNameAndBookId("arshia2313", 23)).thenReturn(library);
		doNothing().when(libraryRepository).delete(library);
		assertEquals(Constants.BOOK_RELEASED, libraryService.releaseBook("arshia2313", 23));

	}

	@Test
	void testDataNotFound() throws DataNotFoundException {
		when(libraryRepository.findByUserNameAndBookId("arshia2313", 23)).thenReturn(null);
		doNothing().when(libraryRepository).delete(library);
		assertThrows(DataNotFoundException.class, () -> {
			libraryService.releaseBook("arshia2313", 23);
		});
	}

}
