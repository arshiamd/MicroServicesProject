package com.epam.librarymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.librarymanagement.client.UserServiceClient;
import com.epam.librarymanagement.dto.UserDTO;
import com.epam.librarymanagement.entity.Book;
import com.epam.librarymanagement.entity.Library;
import com.epam.librarymanagement.entity.User;
import com.epam.librarymanagement.repository.LibraryRepository;
import com.epam.librarymanagement.service.BookService;
import com.epam.librarymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	private BookService bookService;

	@Autowired
	private LibraryRepository libraryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<UserDTO> findAll() {

		List<User> users = userServiceClient.findAll().getBody();
		return mapper.map(users, new TypeToken<List<UserDTO>>() {
		}.getType());
//		users.forEach(user -> {
//			List<Book> books = MapUserBook(user);
//			user.setBooks(books);
//		});

	}

	@Override
	public UserDTO findUserById(String userName) {
		User user = userServiceClient.findUserById(userName).getBody();
		List<Book> books = MapUserBook(user);
		user.setBooks(books);
		return mapper.map(user, UserDTO.class);
	}

	private List<Book> MapUserBook(User user) {
		List<Book> books = new ArrayList<>();
		List<Library> libraryUser = libraryRepository.findByUserName(user.getUserName());
		if (!libraryUser.isEmpty()) {
			libraryUser.forEach(item -> books.add(bookService.findBookById(item.getBookId()).getBody()));
		}
		return books;
	}

	@Override
	public ResponseEntity<String> deleteUser(String userName) {
		libraryRepository.deleteAll(libraryRepository.findByUserName(userName));
		return userServiceClient.deleteUser(userName);
	}

	@Override
	public ResponseEntity<String> addUser(UserDTO userDTO) {
		return userServiceClient.addUser(userDTO);
	}

	@Override
	public ResponseEntity<String> updateUser(String userName, UserDTO userDTO) {
		return userServiceClient.updateUser(userName, userDTO);
	}

}
