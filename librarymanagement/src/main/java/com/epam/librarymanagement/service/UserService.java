package com.epam.librarymanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.librarymanagement.dto.UserDTO;
import com.epam.librarymanagement.entity.User;

public interface UserService {

	public List<UserDTO> findAll();

	public UserDTO findUserById(String userName);
	
	public ResponseEntity<String> deleteUser(String userName);
	
	public ResponseEntity<String> addUser(UserDTO userDTO);
	
	public ResponseEntity<String> updateUser(String userName, UserDTO userDTO);
}
