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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"03. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@Slf4j
public class UserMainController {

    private final UserService userService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping("/users")
    public ListResult<User> getUsers() {

        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userService.getUsers());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회(get userId by Token)", notes = "로그인 성공 후 access_token으로 회원을 조회한다")
    @GetMapping("/user")
    public SingleResult<User> getUserByToken(){

        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userService.getUserByUserId(userId).orElseThrow(UserNotFoundException::new));
    }

    @ApiOperation(value = "회원 단건 조회(get user by userPk)", notes = "회원번호(userPk)로 회원을 조회한다")
    @GetMapping("/user/{userPk}")
    public SingleResult<User> getUserById(@ApiParam(value = "회원번호", required = true) @PathVariable long userPk){

        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userService.getUserById(userPk).orElseThrow(UserNotFoundException::new));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping("/user")
    public SingleResult<User> update(
            @ApiParam(value = "회원번호", required = true) @RequestParam Long userPk,
            @ApiParam(value = "회원이름", required = true) @RequestParam String userName) {

        User user = User.builder()
                .userPk(userPk)
                .userName(userName)
                .build();

        return responseService.getSingleResult(userService.updateUser(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원번호(userPk)로 회원정보를 삭제한다")
    @DeleteMapping("/user/{userPk}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long userPk) {

        userService.deleteUserById(userPk);

        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}

