package com.jd.gowith.biz.user.repository.impl;

import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Long>, UserRepository<User> {
    //Optional<User> findById(Long id);
    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdAndUserOathPrvdr(String userId, String provider);
}
