package com.epam.librarymanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.librarymanagement.entity.Library;


public interface LibraryRepository extends CrudRepository<Library, Integer>{

	public List<Library> findByBookId(int bookId);

	public List<Library> findByUserName(String userName);

	public boolean existsByUserNameAndBookId(String userName, int bookId);

	public Library findByUserNameAndBookId(String username, int bookId);

}
