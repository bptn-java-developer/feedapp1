package com.bptn.bean;

public class Post{

	private String postID;

	private String postType;

	private String post;

	private String usernameKey;    

	public Post() {
	}
	
	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPost() {
		return post;
	}


	public void setPost(String post) {
		this.post = post;
	}

	public String getUsernameKey() {
		return usernameKey;
	}

	public void setUsernameKey(String usernameKey) {
		this.usernameKey = usernameKey;
	}

}