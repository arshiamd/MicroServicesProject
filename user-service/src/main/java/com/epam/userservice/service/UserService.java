package com.epam.userservice.service;

import java.util.List;

import com.epam.userservice.dto.UserDTO;
import com.epam.userservice.entity.User;

public interface UserService {
	
	public String save(UserDTO userDTO);

	public String update(String userName, UserDTO userDTO);

	public String delete(String userName);

	public UserDTO findUserById(String userName);

	public List<UserDTO> findAllUsers();
}
