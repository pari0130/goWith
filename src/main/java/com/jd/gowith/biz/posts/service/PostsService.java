package com.jd.gowith.biz.posts.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.jd.gowith.biz.posts.model.Posts;

public interface PostsService {

	List<Posts> getPosts();

	Posts createPosts(Posts posts);

	Optional<Posts> getPostsById(Long postsId);

	Posts updatePosts(Posts posts);

	void deletePostsById(Long postsId);
}
