package com.jd.gowith.biz.oauth.service;

import com.jd.gowith.biz.oauth.model.KakaoProfile;
import com.jd.gowith.biz.oauth.model.RetKakaoAuth;

public interface OauthService {
    KakaoProfile getKakaoProfile(String accessToken);

    RetKakaoAuth getKakaoTokenInfo(String code);
}
