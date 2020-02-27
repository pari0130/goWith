package com.jd.gowith.repo;

import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserJpaRepoTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenFindByUid_thenReturnUser() {
        String userId = "angrydaddy@gmail.com";
        String userName = "angrydaddy";
        // given
        userService.createUser(User.builder()
                .userId(userId)
                .userPassword(passwordEncoder.encode("1234"))
                .userName(userName)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        // when
        Optional<User> user = userService.getUserByUserId(userId);
        // then
        assertNotNull(user);// user객체가 null이 아닌지 체크
        assertTrue(user.isPresent()); // user객체가 존재여부 true/false 체크
        assertEquals(user.get().getUsername(), userName); // user객체의 name과 name변수 값이 같은지 체크
        assertThat(user.get().getUsername(), is(userName)); // user객체의 name과 name변수 값이 같은지 체크
    }
}
