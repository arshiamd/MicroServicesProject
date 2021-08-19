package com.epam.libraryservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.libraryservice.client.BookServiceClient;
import com.epam.libraryservice.client.UserServiceClient;
import com.epam.libraryservice.dto.UserDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.entity.User;
import com.epam.libraryservice.repository.LibraryRepository;
import com.epam.libraryservice.service.impl.BookServiceImpl;
import com.epam.libraryservice.service.impl.UserServiceImpl;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private BookServiceImpl bookService;

	@MockBean
	private LibraryRepository libraryRepository;

	@MockBean
	private UserServiceClient userServiceClient;
	
	@MockBean
	private BookServiceClient bookServiceClient;

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

		List<Book> bookList = new ArrayList<>();
		bookList.add(book);
		user.setBooks(bookList);
	}

	@Test
	void testAddUser() {
		when(userServiceClient.addUser(userDto)).thenReturn(new ResponseEntity<>("User Added!", HttpStatus.OK));
		assertEquals("User Added!", userService.addUser(userDto).getBody());

	}

	@Test
	void testDisplayUserById() {
		List<Library> record = new ArrayList<>();
		record.add(library);
		when(userServiceClient.findUserById(any())).thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
		when(libraryRepository.findByUserName(any())).thenReturn(record);
		when(bookServiceClient.findBookById(23)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		assertEquals(book,bookService.findBookById(23).getBody());
		assertEquals(book,userService.findUserById(any()).getBooks().get(0));




	}

	@Test
	void testDisplayAllUsers() {
		List<User> users = new ArrayList<>();
		users.add(user);
		List<Library> record = new ArrayList<>();
		record.add(library);
//		when(libraryRepository.findByUserName("arshia2313")).thenReturn(record);
		when(userServiceClient.findAll()).thenReturn(new ResponseEntity<>(users,HttpStatus.OK));
//		when(bookServiceClient.findBookById(23)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
		assertEquals(users,userService.findAll());
//		assertEquals(book,userService.getUsers().get(0).getBooks().get(0));

	}

	@Test
	void testUpdateUsers() {
		when(userServiceClient.updateUser(userDto.getUserName(), userDto))
				.thenReturn(new ResponseEntity<>("User Updated!", HttpStatus.OK));
		assertEquals("User Updated!", userService.updateUser(userDto.getUserName(), userDto).getBody());

	}

	@Test
	void testDeleteUsers() {
		List<Library> record = new ArrayList<>();
		record.add(library);
		when(libraryRepository.findByUserName("arshia2313")).thenReturn(record);
		doNothing().when(libraryRepository).deleteAll(record);
		when(userServiceClient.deleteUser("arshia2313"))
				.thenReturn(new ResponseEntity<>("User Deleted!", HttpStatus.OK));
		assertEquals("User Deleted!", userService.deleteUser("arshia2313").getBody());
	}

}