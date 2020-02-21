package com.jd.gowith.biz.user.service;

import com.jd.gowith.biz.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

	List<User> getUsers();

	User createUser(User user);

	Optional<User> getUserById(Long userPk);

	Optional<User> getUserByUserId(String email);

	User updateUser(User user);

	void deleteUserById(Long userId);
}
