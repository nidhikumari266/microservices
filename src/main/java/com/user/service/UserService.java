package com.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.user.entities.User;

public interface UserService {

	User createUser(User user);

	ResponseEntity<List<User>> getUserList();

	User findById(String id);

}
