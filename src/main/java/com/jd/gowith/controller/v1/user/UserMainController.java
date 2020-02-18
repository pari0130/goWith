package com.jd.gowith.controller.v1.user;

import com.jd.gowith.biz.response.model.CommonResult;
import com.jd.gowith.biz.response.model.ListResult;
import com.jd.gowith.biz.response.model.SingleResult;
import com.jd.gowith.biz.response.service.ResponseService;
import com.jd.gowith.biz.user.model.User;
import com.jd.gowith.biz.user.service.UserService;
import com.jd.gowith.common.exception.user.UserNotFoundException;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
@Slf4j
public class UserMainController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseService responseService;

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
//    @GetMapping
//    public ListResult<User> findAllUser() {
//
//        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
//        return responseService.getListResult(userService.getUsers());
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(msrl)로 회원을 조회한다")
//    @GetMapping(value = "/user")
//    public SingleResult<User> findUser() {
//
//        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userId = authentication.getName();
//
//        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
//        return responseService.getSingleResult(userService.getUserById(userId).orElseThrow(UserNotFoundException::new));
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
//    @PutMapping(value = "/user")
//    public SingleResult<User> modify(
//            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String userId = authentication.getName();
//
//        User user = userService.getUserById(userId).orElseThrow(UserNotFoundException::new);
//        user.setUserName(name);
//
//        return responseService.getSingleResult(userService.createUser(user));
//    }
//
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
//    })
//    @ApiOperation(value = "회원 삭제", notes = "회원번호(userId)로 회원정보를 삭제한다")
//    @DeleteMapping("/{id}")
//    public CommonResult delete(
//            @ApiParam(value = "회원번호", required = true) @PathVariable long userId) {
//
//        userService.deleteUserById(userId);
//
//        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
//        return responseService.getSuccessResult();
//    }
}

