package com.epam.libraryservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.libraryservice.dto.UserDTO;
import com.epam.libraryservice.entity.User;

public interface UserService {

	public List<User> findAll();

	public User findUserById(String userName);
	
	public ResponseEntity<String> deleteUser(String userName);
	
	public ResponseEntity<String> addUser(UserDTO userDTO);
	
	public ResponseEntity<String> updateUser(String userName, UserDTO userDTO);
}
