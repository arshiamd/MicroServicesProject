package com.epam.libraryservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.libraryservice.client.UserServiceClient;
import com.epam.libraryservice.dto.UserDTO;
import com.epam.libraryservice.entity.Book;
import com.epam.libraryservice.entity.Library;
import com.epam.libraryservice.entity.User;
import com.epam.libraryservice.repository.LibraryRepository;
import com.epam.libraryservice.service.BookService;
import com.epam.libraryservice.service.UserService;

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
	public List<User> findAll() {

		List<User> users = userServiceClient.findAll().getBody();
		return users;
//		users.forEach(user -> {
//			List<Book> books = MapUserBook(user);
//			user.setBooks(books);
//		});

	}

	@Override
	public User findUserById(String userName) {
		User user = userServiceClient.findUserById(userName).getBody();
		List<Book> books = MapUserBook(user);
		user.setBooks(books);
		return user;
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
