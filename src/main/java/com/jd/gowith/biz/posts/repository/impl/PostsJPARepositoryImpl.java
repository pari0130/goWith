package com.jd.gowith.biz.posts.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.jd.gowith.biz.posts.model.Posts;
import com.jd.gowith.biz.posts.repository.PostsRepository;

@Primary
@Repository
public class PostsJPARepositoryImpl implements PostsRepository{

	@Autowired
	@Lazy
	private PostsJPARepository postsJPARepository;
	
	@Override
	public List<Posts> list() {
		return postsJPARepository.findAll();
	}

	@Override
	public Posts create(Posts posts) {
		return postsJPARepository.save(posts);
	}

	@Override
	public Optional<Posts> get(Long postsId) {
		return postsJPARepository.findById(postsId);
	}

	@Override
	public Posts update(Posts posts) {
		return postsJPARepository.save(posts);
	}

	@Override
	public void delete(Long postsId) {
		postsJPARepository.deleteById(postsId);
	}
}
