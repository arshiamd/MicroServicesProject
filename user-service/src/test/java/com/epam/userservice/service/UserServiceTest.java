package com.epam.userservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.epam.userservice.constants.Constants;
import com.epam.userservice.dto.UserDTO;
import com.epam.userservice.entity.User;
import com.epam.userservice.exception.UserNotFoundException;
import com.epam.userservice.repository.UserRepository;
import com.epam.userservice.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	@Mock
	ModelMapper mapper;
	
	private User user;
	private UserDTO userDTO;

	@BeforeEach
	void setup() {
		
		user = new User();
		user.setUserName("arshia2313");
		user.setEmail("arshiamd.777@gmail.com");
		user.setName("Arshia Mohammad");
		
		userDTO = new UserDTO();
		userDTO.setUserName("arshia2313");
		userDTO.setEmail("arshiamd.777@gmail.com");
		userDTO.setName("Arshia Mohammad");
		
	}


	@Test
	void testAddUser() {
		when(userRepository.existsById("arshia2313")).thenReturn(false);
		when(userRepository.save(user)).thenReturn(user);
		when(mapper.map(userDTO, User.class)).thenReturn(user);
		assertEquals(Constants.USER_ADDED, userService.save(userDTO));

	}
	
	@Test
	void testUserAlreadyExists() {
		when(userRepository.existsById("arshia2313")).thenReturn(true);
		when(mapper.map(userDTO, User.class)).thenReturn(user);
		assertEquals(Constants.USER_ALREADY_EXISTS, userService.save(userDTO));

	}
	
	@Test
	void testDisplayUserById_UserExistsCase() {
		Optional<User> user1 = Optional.of(new User());
		user1.get().setUserName("arshia2313");
		user1.get().setEmail("arshiamd.777@gmail.com");
		user1.get().setName("Arshia Mohammad");
		userRepository.save(user);
		
		when(userRepository.findById("arshia2313")).thenReturn(user1);
		when(mapper.map(user1.get(), UserDTO.class)).thenReturn(userDTO);
		assertEquals(userDTO,userService.findUserById("arshia2313"));
	}
	
	@Test
	void testDisplayUserById_UserNotExistsCase() {
		Optional<User> user1 = Optional.of(new User());
//		when(userRepository.findById("arshia231")).thenReturn(Optional.empty);
		when(mapper.map(user1.get(), UserDTO.class)).thenReturn(userDTO);
		assertThrows(UserNotFoundException.class, () -> {
			userService.findUserById("arshia231");
		});

	}
	
	@Test
	void testDisplayUsers_UsersPresentCondition() throws UserNotFoundException {
		List<User> users = new ArrayList<>();
		List<UserDTO> usersDTO = new ArrayList<>();
		users.add(user);
		when(userRepository.findAll()).thenReturn(users);
		when(mapper.map(users, new TypeToken<List<UserDTO>>() {}.getType())).thenReturn(usersDTO);
		assertEquals(userDTO,userService.findAllUsers().get(0));
	}
	
	@Test
	void testDeleteUsers_WhenUserPresent() {
		when(userRepository.findById("arshia231")).thenReturn(Optional.of(user));
		doNothing().when(userRepository).deleteById("arshia231");
		assertEquals(Constants.USER_DELETED, userService.delete("arshia231"));

	}
	
	@Test
	void testDeleteUsers_WhenUserNotPresent() {
		when(userRepository.existsById("arshia231")).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> {
			userService.delete("arshia231");
		});

	}
	
	@Test
	void testUpdateUser_Present() {
		when(userRepository.existsById("arshia2313")).thenReturn(true);
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(Constants.USER_UPDATED, userService.update("arshia2313",userDTO));

	}
	
	@Test
	void testUpdateUser_NotPresent() {
		when(userRepository.existsById("arshia231678")).thenReturn(true);
		when(mapper.map(userDTO, User.class)).thenReturn(user);
		userService.update("arshia231678",userDTO);
//		assertThrows(UserNotFoundException.class, () -> {
//			userService.update("arshia231678",userDTO);
//		});
	}


//	@Test
//	void testDisplayUserById() {
//		List<Library> record = new ArrayList<>();
//		record.add(library);
//		when(userServiceClient.findUserById(any())).thenReturn(new ResponseEntity<>(user,HttpStatus.OK));
//		when(libraryRepository.findByUserName(any())).thenReturn(record);
//		when(bookServiceClient.findBookById(23)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
//		assertEquals(book,bookService.findBookById(23).getBody());
//		assertEquals(book,userService.findUserById(any()).getBooks().get(0));
//
//
//
//
//	}
//
//	@Test
//	void testDisplayAllUsers() {
//		List<User> users = new ArrayList<>();
//		users.add(user);
//		List<Library> record = new ArrayList<>();
//		record.add(library);
////		when(libraryRepository.findByUserName("arshia2313")).thenReturn(record);
//		when(userServiceClient.findAll()).thenReturn(new ResponseEntity<>(users,HttpStatus.OK));
////		when(bookServiceClient.findBookById(23)).thenReturn(new ResponseEntity<>(book,HttpStatus.OK));
//		assertEquals(users,userService.findAll());
////		assertEquals(book,userService.getUsers().get(0).getBooks().get(0));
//
//	}
//
//	@Test
//	void testUpdateUsers() {
//		when(userServiceClient.updateUser(userDto.getUserName(), userDto))
//				.thenReturn(new ResponseEntity<>("User Updated!", HttpStatus.OK));
//		assertEquals("User Updated!", userService.updateUser(userDto.getUserName(), userDto).getBody());
//
//	}
//
//	@Test
//	void testDeleteUsers() {
//		List<Library> record = new ArrayList<>();
//		record.add(library);
//		when(libraryRepository.findByUserName("arshia2313")).thenReturn(record);
//		doNothing().when(libraryRepository).deleteAll(record);
//		when(userServiceClient.deleteUser("arshia2313"))
//				.thenReturn(new ResponseEntity<>("User Deleted!", HttpStatus.OK));
//		assertEquals("User Deleted!", userService.deleteUser("arshia2313").getBody());
//	}

}