package com.jd.gowith.controller.oauth;

import com.google.gson.Gson;
import com.jd.gowith.biz.oauth.service.OauthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Api(tags = {"02. oAuth"})
@RequiredArgsConstructor
@Controller
@RequestMapping("/oauth")
public class OauthController {

    private final Environment env;
    private final RestTemplate restTemplate;
    private final Gson gson;
    private final OauthService oauthService;

    @Value("${spring.base.url}")
    private String baseUrl;

    @Value("${spring.oauth.kakao.client_id}")
    private String kakaoClientId;

    @Value("${spring.oauth.kakao.redirect}")
    private String kakaoRedirect;

    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인 페이지로 이동한다.")
    @GetMapping(value = "/kakao/login")
    public ModelAndView socialLogin(ModelAndView mav) {

        StringBuilder loginUrl = new StringBuilder()
                .append(env.getProperty("spring.oauth.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(baseUrl).append(kakaoRedirect);

        mav.addObject("loginUrl", loginUrl);
        mav.setViewName("oauth/kakaoLogin");
        return mav;
    }

    @ApiOperation(value = "카카오 로그인 리다이렉트", notes = "카카오 로그인 리다이렉트 페이지로 이동한다.")
    @GetMapping(value = "/kakao/redirect")
    public ModelAndView redirectKakao(ModelAndView mav, @RequestParam String code) {
        mav.addObject("authInfo", oauthService.getKakaoTokenInfo(code));
        mav.setViewName("oauth/kakaoRedirect");
        return mav;
    }
}
