package com.bptn.bean;

import java.util.List;

public class PostSpecialFormat {

	UserIdBean userId;
	
	List<Post> posts;

	public UserIdBean getUserId() {
		return userId;
	}

	public void setUserId(UserIdBean userId) {
		this.userId = userId;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
	
}
