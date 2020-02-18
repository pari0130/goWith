package com.jd.gowith.biz.posts.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.gowith.biz.posts.model.Posts;
import com.jd.gowith.biz.posts.repository.PostsRepository;
import com.jd.gowith.biz.posts.service.PostsService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
public class PostsServiceImpl implements PostsService{

	@Autowired
	private PostsRepository postsRepository;
	
	@Override
	public List<Posts> getPosts() {
		return postsRepository.list();
	}

	@Override
    public Posts createPosts(Posts posts) {
        return postsRepository.create(posts);
    }
	
	@Override
	public Optional<Posts> getPostsById(Long postsId) {
        return postsRepository.get(postsId);
    }
	
	@Override
	public Posts updatePosts(Posts posts) {
		return postsRepository.update(posts);
	}
	

	@Override
    public void deletePostsById(Long postsId) {
		postsRepository.delete(postsId);
    }
	
//  @Transactional
//  public Long save(PostsSaveRequestDto dto){
//      return postsRepository.save(dto.toEntity()).getId();
//  }
//  
//  @Transactional(readOnly = true)
//  public List<PostsMainResponseDto> findAllDesc() {
//      return postsRepository.findAllDesc()
//              .map(PostsMainResponseDto::new)
//              .collect(Collectors.toList());
//  }
	
}
