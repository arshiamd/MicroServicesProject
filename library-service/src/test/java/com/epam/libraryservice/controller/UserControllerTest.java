package com.epam.libraryservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.libraryservice.dto.UserDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.entity.User;
import com.epam.libraryservice.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	UserServiceImpl userService;

	private String userEndPoint = "http://localhost:8081/library/users";

	private Library library;
	private Book book;
	private User user;
	private UserDTO userDto;

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
	void testAddUser() throws JsonProcessingException, Exception {
		when(userService.addUser(Mockito.any())).thenReturn(new ResponseEntity<>("User Added!", HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.post(userEndPoint).content(objectMapper.writeValueAsString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User Added!"));

	}

	@Test
	void testDisplayUserById() throws Exception {
		when(userService.findUserById("arshia2313")).thenReturn(user);
		mockMvc.perform(
				MockMvcRequestBuilders.get(userEndPoint + "/arshia2313").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.userName").value("arshia2313"));

	}

	@Test
	void testDisplayAllUsers() throws Exception {
		List<User> users = new ArrayList<>();
		users.add(user);
		when(userService.findAll()).thenReturn(users);
		mockMvc.perform(MockMvcRequestBuilders.get(userEndPoint).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].userName", is("arshia2313")));

	}

	@Test
	void testUpdateUser() throws JsonProcessingException, Exception {
		when(userService.updateUser(Mockito.any(), Mockito.any()))
				.thenReturn(new ResponseEntity<>("User Updated!", HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.put(userEndPoint + "/arshia2313")
				.content(objectMapper.writeValueAsString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User Updated!"));

	}

	@Test
	void testDeleteUser() throws Exception {
		when(userService.deleteUser("arshia2313")).thenReturn(new ResponseEntity<>(
				"User Deleted!", HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.delete(userEndPoint + "/arshia2313").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("User Deleted!"));

	}

}
