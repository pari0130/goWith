package com.jd.gowith.biz.response.service;

import com.jd.gowith.biz.response.model.CommonResult;
import com.jd.gowith.biz.response.model.ListResult;
import com.jd.gowith.biz.response.model.SingleResult;

import java.util.List;

public interface ResponseService {

    // 단일건 결과를 처리하는 메소드
    <T> SingleResult<T> getSingleResult(T data);

    // 다중건 결과를 처리하는 메소드
    <T> ListResult<T> getListResult(List<T> list);

    // 성공 결과만 처리하는 메소드
    CommonResult getSuccessResult();

    // 실패 결과만 처리하는 메소드
    CommonResult getFailResult(int code, String msg);
}
