package com.jd.gowith.biz.user.repository.impl;

import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Long>, UserRepository<User> {
}
