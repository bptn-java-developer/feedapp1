package com.bptn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.exceptions.InvalidPostException;
import com.bptn.jpa.Post;
import com.bptn.service.PostService;

@RestController
public class PostController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private PostService postService;

    @GetMapping(value = "/")
    public String ping() {
        
    	logger.debug("Executing ping()");
        
    	String str = "Feed App up and running";
    	
    	return str;
    }

    @GetMapping(value = "/posts/username/{username}")
    public List<Post> getPostsByUsername(@PathVariable("username") String username) {
        
    	logger.debug("Executing getPostsByUsername API");
        
    	List<Post> posts = this.postService.getPostsByUsername(username);

        return posts;
    }

    @GetMapping(value = "/posts/postID/{postID}")
    public ResponseEntity<?> getPostsByPostId(@PathVariable("postID") String postID) {
        
    	logger.debug("Executing getPostsByPostId API");
        
    	Post post;
        
        try {
            post = this.postService.getPostsByPostId(postID);
        } catch (InvalidPostException e) {
            logger.error("Unable to find Post by postID, cause: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping(value = "/posts/postType/{postType}")
    public List<Post> getPostsByPostType(@PathVariable("postType") String postType) {
        
    	logger.debug("Executing getPostsByPostType API");
        
    	List<Post> posts = this.postService.getPostsByPostType(postType);

        return posts;
    }

}
