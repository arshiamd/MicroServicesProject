package com.epam.userservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.userservice.entity.User;

public interface UserRepository extends CrudRepository<User, String>{

}
