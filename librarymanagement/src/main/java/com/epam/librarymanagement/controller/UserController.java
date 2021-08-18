package com.epam.librarymanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.librarymanagement.dto.UserDTO;
import com.epam.librarymanagement.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping(value = "/library/users")
	public ResponseEntity<List<UserDTO>> findAllUsers() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/library/users/{userName}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable String userName) {
		return new ResponseEntity<>(userService.findUserById(userName), HttpStatus.OK);
	}

	@PostMapping(value = "/library/users")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDTO userDTO) {
		return userService.addUser(userDTO);
	}

	@DeleteMapping(value = "/library/users/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName) {
		return userService.deleteUser(userName);
	}

	@PutMapping(value = "/library/users/{userName}")
	public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody UserDTO userDTO) {
		return userService.updateUser(userName, userDTO);
	}

}
