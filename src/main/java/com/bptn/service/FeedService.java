package com.bptn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;
import com.bptn.repository.FeedRepository;

@Service
public class FeedService {

	@Autowired
	FeedRepository feedRepository;

	public List<Post> getPostsbyUsername(String username){
		
		List<Post> posts = this.feedRepository.findByUserId(new UserID(username));
		
		posts = this.removeEmptyPosts(posts);
		/*
		List<com.bptn.bean.Post> posts2 = new ArrayList<>();
		
		for (Post p: posts) {
			
			com.bptn.bean.Post post = new com.bptn.bean.Post();
			
			post.setPostID(p.getPostID());
			post.setPostType(p.getPostType());
			post.setPost(p.getPost());
			post.setUsernameKey(p.getUserId().getUsername());
		}
		
		PostSpecialFormat psf = new PostSpecialFormat();
		
		UserIdBean userId = new UserIdBean();
		userId.setUsername(username);
		
		psf.setUserId(userId);
		psf.setPosts(posts2);
		*/
		return posts;
	}
	
	List<Post> removeEmptyPosts(List<Post> posts){
		
		posts.removeIf(p -> p.getPostType()==null || p.getPostType().isEmpty() );

		return posts;
	}
}
