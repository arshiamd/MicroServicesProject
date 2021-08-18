package com.epam.usermanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.epam.usermanagement.entity.User;

public interface UserRepository extends CrudRepository<User, String>{

}
