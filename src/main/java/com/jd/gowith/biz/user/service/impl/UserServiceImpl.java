package com.jd.gowith.biz.user.service.impl;

import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.repository.UserRepository;
import com.jd.gowith.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		return userRepository.list();
	}

	@Override
    public User createUser(User user) {
        return userRepository.create(user);
    }
	
	@Override
	public Optional<User> getUserById(Long userId) {
        return userRepository.get(userId);
    }
	
	@Override
	public User updateUser(User user) {
		return userRepository.update(user);
	}

	@Override
    public void deleteUserById(Long userId) {
		userRepository.delete(userId);
    }
	
//  @Transactional
//  public Long save(PostsSaveRequestDto dto){
//      return userRepository.save(dto.toEntity()).getId();
//  }
//  
//  @Transactional(readOnly = true)
//  public List<PostsMainResponseDto> findAllDesc() {
//      return userRepository.findAllDesc()
//              .map(PostsMainResponseDto::new)
//              .collect(Collectors.toList());
//  }
	
}
