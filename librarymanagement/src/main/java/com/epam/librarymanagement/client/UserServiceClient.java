package com.epam.librarymanagement.client;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.librarymanagement.dto.UserDTO;
import com.epam.librarymanagement.entity.User;

@FeignClient(name = "userService", url = "http://localhost:8082")
//@RibbonClient(name = "userService")
public interface UserServiceClient {
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> findAll();

	@GetMapping(value = "/users/{userName}")
	public ResponseEntity<User> findUserById(@PathVariable String userName);

	@DeleteMapping(value = "/users/{userName}")
	public ResponseEntity<String> deleteUser(@PathVariable String userName);

	@PostMapping(value = "/users")
	public ResponseEntity<String> addUser(@RequestBody @Valid UserDTO userDTO);

	@PutMapping(value = "/users/{userName}")
	public ResponseEntity<String> updateUser(@PathVariable String userName, @RequestBody @Valid UserDTO userDTO);

}
