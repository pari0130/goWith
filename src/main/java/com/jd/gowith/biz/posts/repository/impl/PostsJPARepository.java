package com.jd.gowith.biz.posts.repository.impl;

import com.jd.gowith.biz.posts.repository.PostsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jd.gowith.biz.posts.model.Posts;

public interface PostsJPARepository extends JpaRepository<Posts, Long>, PostsRepository<Posts> {
}
