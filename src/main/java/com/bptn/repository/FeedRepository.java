package com.bptn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;

public interface FeedRepository extends JpaRepository<Post, String> {

	List<Post> findByUserId(UserID userId); // Replaces findByUsername()
	List<Post> findByPostType(String postType);
}
