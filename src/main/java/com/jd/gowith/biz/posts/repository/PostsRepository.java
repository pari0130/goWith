package com.jd.gowith.biz.posts.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;

import com.jd.gowith.biz.posts.model.Posts;

public interface PostsRepository<T>{

	List<Posts> list();
	// CRUD
	Posts create(Posts posts);
	Optional<Posts> get(Long postsId);
	Posts update(Posts posts);
	void delete(Long postsId);
	
	// 실제로 위 코드는 SpringDataJpa에서 제공하는 기본 메소드만으로 해결할 수 있는데요.
	// 굳이 @Query를 쓴 이유는, SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 되는것을 보여주기 위함입니다. 
//	 @Query("SELECT p " +
//	            "FROM User p " +
//	            "ORDER BY p.id DESC")
//	 Stream<User> findAllDesc();
}
