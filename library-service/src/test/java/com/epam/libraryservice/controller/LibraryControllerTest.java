package com.epam.libraryservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.epam.libraryservice.constants.Constants;
import com.epam.libraryservice.service.LibraryServiceTest;
import com.epam.libraryservice.service.impl.LibraryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class LibraryControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	LibraryServiceImpl libraryService;

	private String libraryEndPoint = "http://localhost:8081/library/";

	@Test
	void testIssueBook() throws Exception {
		when(libraryService.issueBook("arshia2313", 23)).thenReturn(Constants.BOOK_ISSUED_TO_USER);
		mockMvc.perform(MockMvcRequestBuilders.post(libraryEndPoint + "/users/arshia2313/books/23")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(Constants.BOOK_ISSUED_TO_USER));
		when(libraryService.issueBook("arshia2313", 23)).thenReturn(Constants.BOOK_ALREADY_ISSUED);
		mockMvc.perform(MockMvcRequestBuilders.post(libraryEndPoint + "/users/arshia2313/books/23")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(Constants.BOOK_ALREADY_ISSUED));
	}

	@Test
	void testReleaseBook() throws Exception {
		when(libraryService.releaseBook("arshia2313", 23)).thenReturn(Constants.BOOK_RELEASED);
		mockMvc.perform(MockMvcRequestBuilders.delete(libraryEndPoint + "/users/arshia2313/books/23")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(Constants.BOOK_RELEASED));
	}

}
