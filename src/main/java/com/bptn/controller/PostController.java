package com.bptn.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bptn.exceptions.InvalidPostException;
import com.bptn.exceptions.InvalidRequestException;
import com.bptn.jpa.Post;
import com.bptn.repository.PostRepository;
import com.bptn.request.PostRequest;
import com.bptn.service.PostService;

@RestController
public class PostController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/")
    public String ping() {
        
    	logger.debug("Executing ping()");
        
    	String str = "Feed App up and running";
    	
    	return str;
    }

    @GetMapping(value = "/posts/usernam/{username}")
    public List<Post> getPostsByUsername(@PathVariable("username") String username) {
        
    	logger.debug("Executing getPostsByUsername API");
        
    	List<Post> posts = this.postService.getPostsByUsername(username);

        return posts;
    }

    @GetMapping(value = "/posts/postid/{postID}")
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

    @GetMapping(value = "/posts/posttype/{postType}")
    public List<Post> getPostsByPostType(@PathVariable("postType") String postType) {
        
    	logger.debug("Executing getPostsByPostType API");
        
    	List<Post> posts = this.postService.getPostsByPostType(postType);

        return posts;
    }

    @DeleteMapping(value = "/posts/delete/{postID}")
    public void deletePosts(@PathVariable("postID") String postID) {
    	
    	logger.debug("Executing Delete Posts API");
    	
    	this.postRepository.deleteById(postID);
    	
    	logger.debug("Post Deleted");
    }
    
    @Transactional
    @DeleteMapping(value = "/posts/delete2/{postType}")
    public void deletePosts2(@PathVariable("postType") String postType) {
    	
    	logger.debug("Executing Delete v2 Posts API");
    	
    	this.postRepository.deleteByPostType(postType);
    	
    	logger.debug("Post Deleted");
    }
    
	/*
	 * Sample Request Payload { "fromDate": "2022-09-13", "toDate": "2022-10-13",
	 * "queryKeyword": "newyork", "userName": "username1" }
	 */
	@PostMapping(value = "/posts/create")
	public ResponseEntity<?> saveFeed(@RequestBody PostRequest postRequest) {
		
		logger.debug("Executing saveFeed API");
		
		try {
			this.validateRequest(postRequest);
			//userService.validateUserId(feedPostRequest.getUsername());
			Post feed = this.postService.getPostFromNewsAndSavePost(postRequest);
			
			logger.debug("Post saved successfully.");
			return new ResponseEntity<>(feed, HttpStatus.OK);
			
		} catch (InvalidRequestException ex) {
			
			logger.error("Unable to save feed, cause={}", ex.getMessage());
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	private void validateRequest(PostRequest postRequest) throws InvalidRequestException {
		if (!StringUtils.hasText(postRequest.getFromDate()) && 
			!StringUtils.hasText(postRequest.getToDate())) {
			throw new InvalidRequestException("Invalid request : From Date or to Date is required");
		}
	}
}
