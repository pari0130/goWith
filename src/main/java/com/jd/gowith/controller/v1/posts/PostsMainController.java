package com.jd.gowith.controller.v1.posts;

import com.jd.gowith.biz.response.model.CommonResult;
import com.jd.gowith.biz.response.model.ListResult;
import com.jd.gowith.biz.response.model.SingleResult;
import com.jd.gowith.biz.response.service.ResponseService;
import com.jd.gowith.common.exception.posts.PostsNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jd.gowith.biz.posts.model.Posts;
import com.jd.gowith.biz.posts.service.PostsService;

import lombok.extern.slf4j.Slf4j;

@Api(tags = {"04. posts"})
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/posts")
@Slf4j
public class PostsMainController {

    private final PostsService postsService;
    private final ResponseService responseService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @ApiOperation(value = "User 리스트 조회", notes = "게시글 리스트를 조회한다.")
    @GetMapping
    public ListResult<Posts> list() {
        return responseService.getListResult(postsService.getPosts());
    }

    @ApiOperation(value = "User 입력", notes = "게시글을 입력한다.")
    @PostMapping
    public SingleResult<Posts> create(
            @ApiParam(value = "사용자아이디", required = true) @RequestParam String userId, // email
            @ApiParam(value = "게시글제목", required = true) @RequestParam String title,
            @ApiParam(value = "게시글내용", required = true) @RequestParam String contents) {

        Posts posts = Posts.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .build();

        return responseService.getSingleResult(postsService.createPosts(posts));
    }

    @ApiOperation(value = "User 1개 조회", notes = "게시글 1개를 조회한다.")
    @GetMapping("/{id}")
    public SingleResult<Posts> get(
            @ApiParam(value = "게시글번호", required = true) @RequestParam long postsId,
            @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {

        return responseService.getSingleResult(postsService.getPostsById(postsId).orElseThrow(PostsNotFoundException::new));
        /*return ResponseEntity.ok(posts.get());*/
    }

    @ApiOperation(value = "User 수정", notes = "게시글을 수정한다.")
    @PutMapping("/{id}")
    public SingleResult<Posts> update(
            @ApiParam(value = "게시글번호", required = true) @RequestParam long postsId,
            @ApiParam(value = "게시글제목", required = true) @RequestParam String title,
            @ApiParam(value = "게시글내용", required = true) @RequestParam String contents) {

        Posts posts = Posts.builder()
                .postsId(postsId)
                .title(title)
                .contents(contents)
                .build();

        return responseService.getSingleResult(postsService.updatePosts(posts));
    }

    @ApiOperation(value = "User 삭제", notes = "게시글을 삭제한다.")
    @DeleteMapping("/{id}")
    public CommonResult delete(
            @ApiParam(value = "게시글번호", required = true) @RequestParam long postsId) {

        postsService.deletePostsById(postsId);

        return responseService.getSuccessResult();
    }
}
