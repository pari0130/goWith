package com.jd.gowith.controller.v1.auth;

import com.jd.gowith.biz.response.model.CommonResult;
import com.jd.gowith.biz.response.model.SingleResult;
import com.jd.gowith.biz.response.service.ResponseService;
import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.service.UserService;
import com.jd.gowith.common.config.security.JwtTokenProvider;
import com.jd.gowith.common.exception.auth.AuthEmailSigninFailedException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. Auth"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthSignController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseService responseService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    /*@Autowired
    private final KakaoService kakaoService;*/

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String userId,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String userPassword) {

        User user = userService.getUserByUserId(userId).orElseThrow(AuthEmailSigninFailedException::new);

        if (!passwordEncoder.matches(userPassword, user.getPassword()))
            throw new AuthEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUserPk()), user.getRoles()));
    }

    /*@ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> signinByProvider(
            @ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        User user = userJpaRepo.findByUidAndProvider(String.valueOf(profile.getId()), provider).orElseThrow(CUserNotFoundException::new);
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
    }*/

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String userId, // email
                               @ApiParam(value = "비밀번호", required = true) @RequestParam String userPassword,
                               @ApiParam(value = "이름", required = true) @RequestParam String userName) {

        userService.createUser(User.builder()
                .userId(userId) // email
                .userPassword(passwordEncoder.encode(userPassword))
                .userName(userName)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());

        return responseService.getSuccessResult();
    }

    /*@ApiOperation(value = "소셜 계정 가입", notes = "소셜 계정 회원가입을 한다.")
    @PostMapping(value = "/signup/{provider}")
    public CommonResult signupProvider(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
                                       @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken,
                                       @ApiParam(value = "이름", required = true) @RequestParam String name) {

        KakaoProfile profile = kakaoService.getKakaoProfile(accessToken);
        Optional<User> user = userJpaRepo.findByUidAndProvider(String.valueOf(profile.getId()), provider);
        if (user.isPresent())
            throw new CUserExistException();

        User inUser = User.builder()
                .uid(String.valueOf(profile.getId()))
                .provider(provider)
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userJpaRepo.save(inUser);
        return responseService.getSuccessResult();
    }*/

}
