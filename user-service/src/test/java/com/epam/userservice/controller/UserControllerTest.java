package com.epam.userservice.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Library;
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

import com.epam.userservice.constants.Constants;
import com.epam.userservice.dto.UserDTO;
import com.epam.userservice.entity.User;
import com.epam.userservice.service.impl.UserServiceImpl;
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

	private String userEndPoint = "http://localhost:8081/users";

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
	}

	@Test
	void testAddUser() throws JsonProcessingException, Exception {
		when(userService.save(Mockito.any())).thenReturn(Constants.USER_ADDED);
		mockMvc.perform(MockMvcRequestBuilders.post(userEndPoint).content(objectMapper.writeValueAsString(userDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(Constants.USER_ADDED));

	}

	@Test
	void testDisplayUserById() throws Exception {
		when(userService.findUserById("arshia2313")).thenReturn(userDto);
		mockMvc.perform(
				MockMvcRequestBuilders.get(userEndPoint + "/arshia2313").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.userName").value("arshia2313"));

	}

	@Test
	void testDisplayAllUsers() throws Exception {
		List<UserDTO> users = new ArrayList<>();
		users.add(userDto);
		when(userService.findAllUsers()).thenReturn(users);
		mockMvc.perform(MockMvcRequestBuilders.get(userEndPoint).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].userName", is("arshia2313")));

	}

	@Test
	void testUpdateUser() throws JsonProcessingException, Exception {
		when(userService.update(Mockito.any(), Mockito.any()))
				.thenReturn(Constants.USER_UPDATED);
		mockMvc.perform(MockMvcRequestBuilders.put(userEndPoint + "/arshia2313")
				.content(objectMapper.writeValueAsString(userDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(Constants.USER_UPDATED));

	}

	@Test
	void testDeleteUser() throws Exception {
		when(userService.delete("arshia2313")).thenReturn(Constants.USER_DELETED);
		mockMvc.perform(
				MockMvcRequestBuilders.delete(userEndPoint + "/arshia2313").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value(Constants.USER_DELETED));

	}

}
