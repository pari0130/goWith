package com.jd.gowith.biz.user.service.impl;

import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.repository.UserRepository;
import com.jd.gowith.biz.user.service.UserService;
import com.jd.gowith.common.exception.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
	public Optional<User> getUserById(Long userPk) {
        return userRepository.getUserById(userPk);
    }

	@Override
	public Optional<User> getUserByUserId(String userId) {
		return userRepository.getUserByUserId(userId);
	}
	
	@Override
	public User updateUser(User user) {
		return userRepository.update(user);
	}

	@Override
    public void deleteUserById(Long userId) {
		userRepository.delete(userId);
    }

	public UserDetails loadUserByUsername(String userPk) {
		Optional<UserDetails> userDetails = userRepository.getUserById(Long.valueOf(userPk));

		/*Optional<UserDetails> userDetails = userRepository.getUserById(Long.valueOf(userPk)).map(user ->
				User.builder()
						.username(user.getUserId())
						.password(user.getPassword())
						.authorities(Arrays.asList(new SimpleGrantedAuthority("Test")))
						.build()
		);*/

		return userDetails.orElseThrow(UserNotFoundException::new);
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
