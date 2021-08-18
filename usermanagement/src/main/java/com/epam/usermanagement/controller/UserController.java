package com.epam.usermanagement.controller;

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

import com.epam.usermanagement.entity.User;
import com.epam.usermanagement.exception.UserNotFoundException;
import com.epam.usermanagement.service.impl.UserServiceImpl;
import com.epan.usermanagement.dto.UserDTO;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;

	@PostMapping("users")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDTO userDTO) {
		return new ResponseEntity<>(userService.save(userDTO), HttpStatus.OK);
	}

	@GetMapping("users")
	public ResponseEntity<List<UserDTO>> findAllUsers() throws UserNotFoundException {
		return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
	}

	@GetMapping("users/{userName}")
	public ResponseEntity<UserDTO> findUserById(@PathVariable String userName){
		return new ResponseEntity<>(userService.findUserById(userName), HttpStatus.OK);
	}

	
	@PutMapping("users/{userName}")
	public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody @Valid UserDTO userDTO){
		return new ResponseEntity<>(userService.update(userName,userDTO), HttpStatus.OK);
	}

	@DeleteMapping("users/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName){
		return new ResponseEntity<>(userService.delete(userName), HttpStatus.OK);
	}

}
