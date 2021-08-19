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

import com.epam.libraryservice.dto.BookDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.service.impl.BookServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private BookServiceImpl bookService;

	private Book book;
	private Library library;
	private BookDTO bookDto;

	private String bookEndPoint = "http://localhost:8081/library/books";

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
	void testAddBook() throws Exception {
		when(bookService.addBook(Mockito.any())).thenReturn(new ResponseEntity<>("Book Added!", HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.post(bookEndPoint).content(objectMapper.writeValueAsString(bookDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book Added!"));
	}

	@Test
	void testdDisplayBookWithGivenId() throws Exception {
		when(bookService.findBookById(23)).thenReturn(new ResponseEntity<>(book, HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.get(bookEndPoint + "/23").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.bookId").value(23));

	}

	@Test
	void testDisplayAllBooks() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookService.findAll()).thenReturn(new ResponseEntity<>(books, HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.get(bookEndPoint).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].bookId", is(23)));

	}

	@Test
	void testUpdateBook() throws JsonProcessingException, Exception {
		when(bookService.updateBook(Mockito.anyInt(), Mockito.any()))
				.thenReturn(new ResponseEntity<>("Book Updated!", HttpStatus.OK));
		mockMvc.perform(
				MockMvcRequestBuilders.put(bookEndPoint + "/23").content(objectMapper.writeValueAsString(bookDto))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value("Book Updated!"));

	}

	@Test
	void testDeleteBook() throws Exception {
		when(bookService.deleteBook(23)).thenReturn(
				new ResponseEntity<>("Book Deleted!", HttpStatus.OK));
		mockMvc.perform(MockMvcRequestBuilders.delete(bookEndPoint + "/23").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Book Deleted!"));

	}

}
