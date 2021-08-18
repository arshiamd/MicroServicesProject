package com.epam.usermanagement.service;

import java.util.List;

import com.epam.usermanagement.entity.User;
import com.epan.usermanagement.dto.UserDTO;

public interface UserService {
	
	public String save(UserDTO userDTO);

	public String update(String userName, UserDTO userDTO);

	public String delete(String userName);

	public UserDTO findUserById(String userName);

	public List<UserDTO> findAllUsers();
}
