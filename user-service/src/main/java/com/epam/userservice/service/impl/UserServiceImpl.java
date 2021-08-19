package com.epam.userservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.userservice.constants.Constants;
import com.epam.userservice.dto.UserDTO;
import com.epam.userservice.entity.User;
import com.epam.userservice.exception.NoUsersAvailableException;
import com.epam.userservice.exception.UserNotFoundException;
import com.epam.userservice.repository.UserRepository;
import com.epam.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public String save(UserDTO userDTO) {
		User user = mapper.map(userDTO, User.class);
		String result;
		if (userRepository.existsById(user.getUserName()))
			result = Constants.USER_ALREADY_EXISTS;
		else {
			userRepository.save(user);
			result = Constants.USER_ADDED;
		}
		return result;
	}

	@Override
	public String update(String userName, UserDTO userDTO) {
		User user = mapper.map(userDTO, User.class);
		if (!userRepository.existsById(userName))
			throw new UserNotFoundException(Constants.USER_NOT_FOUND);
		userRepository.save(user);
		return Constants.USER_UPDATED;

	}

	@Override
	public String delete(String userName) {
		Optional<User> user = userRepository.findById(userName);
		if (!user.isPresent())
			throw new UserNotFoundException(Constants.USER_NOT_FOUND);
		userRepository.deleteById(userName);
		return Constants.USER_DELETED;
	}

	@Override
	public UserDTO findUserById(String userName) {
		Optional<User> user = userRepository.findById(userName);
		if (!user.isPresent())
			throw new UserNotFoundException(Constants.USER_NOT_FOUND);
		return mapper.map(user.get(), UserDTO.class);
	}

	@Override
	public List<UserDTO> findAllUsers() {
		List<User> users = (List<User>) userRepository.findAll();
		if(users.isEmpty())
			throw new NoUsersAvailableException(Constants.USERS_UNAVAILABLE);
		return mapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());

	}

}
