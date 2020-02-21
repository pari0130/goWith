package com.jd.gowith.biz.user.repository.impl;

import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Primary
@Repository
public class UserJPARepositoryImpl implements UserRepository {

	@Autowired
	@Lazy
	private UserJPARepository userJPARepository;
	
	@Override
	public List<User> list() {
		return userJPARepository.findAll();
	}

	@Override
	public User create(User user) {
		return userJPARepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Long userPk) { return userJPARepository.findById(userPk); }

	@Override
	public Optional<User> getUserByUserId(String userId) { return userJPARepository.findByUserId(userId); }

	@Override
	public User update(User user) {
		return userJPARepository.save(user);
	}

	@Override
	public void delete(Long userId) {
		userJPARepository.deleteById(userId);
	}
}
