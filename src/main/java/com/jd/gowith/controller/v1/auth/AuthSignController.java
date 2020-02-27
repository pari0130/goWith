package com.jd.gowith.controller.v1.auth;

import com.jd.gowith.biz.oauth.model.KakaoProfile;
import com.jd.gowith.biz.oauth.service.OauthService;
import com.jd.gowith.biz.response.model.CommonResult;
import com.jd.gowith.biz.response.model.SingleResult;
import com.jd.gowith.biz.response.service.ResponseService;
import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.service.UserService;
import com.jd.gowith.common.config.security.JwtTokenProvider;
import com.jd.gowith.common.exception.auth.AuthEmailSigninFailedException;
import com.jd.gowith.common.exception.user.UserExistException;
import com.jd.gowith.common.exception.user.UserNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Api(tags = {"01. Auth"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class AuthSignController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ResponseService responseService;
    private final OauthService oauthService;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String userId,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String userPassword) {

        User user = userService.getUserByUserId(userId).orElseThrow(AuthEmailSigninFailedException::new);

        if (!passwordEncoder.matches(userPassword, user.getPassword()))
            throw new AuthEmailSigninFailedException();

        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUserPk()), user.getRoles()));
    }

    @ApiOperation(value = "소셜 로그인", notes = "소셜 회원 로그인을 한다.")
    @PostMapping(value = "/signin/{provider}")
    public SingleResult<String> signinByProvider(
            @ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
            @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken) {

        KakaoProfile profile = oauthService.getKakaoProfile(accessToken);
        User user = userService.getUserByUserIdAndUserOathPrvdr(String.valueOf(profile.getId()), provider).orElseThrow(UserNotFoundException::new);
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getUserPk()), user.getRoles()));
    }

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

    @ApiOperation(value = "소셜 계정 가입", notes = "소셜 계정 회원가입을 한다.")
    @PostMapping(value = "/signup/{provider}")
    public CommonResult signupProvider(@ApiParam(value = "서비스 제공자 provider", required = true, defaultValue = "kakao") @PathVariable String provider,
                                       @ApiParam(value = "소셜 access_token", required = true) @RequestParam String accessToken,
                                       @ApiParam(value = "이름", required = true) @RequestParam String userName) {

        KakaoProfile profile = oauthService.getKakaoProfile(accessToken);
        Optional<User> user = userService.getUserByUserIdAndUserOathPrvdr(String.valueOf(profile.getId()), provider);
        if (user.isPresent())
            throw new UserExistException();

        User inUser = User.builder()
                .userId(String.valueOf(profile.getId()))
                .userOathPrvdr(provider)
                .userName(userName)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        userService.createUser(inUser);
        return responseService.getSuccessResult();
    }

}
