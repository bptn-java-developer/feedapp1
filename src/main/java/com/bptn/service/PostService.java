package com.bptn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.exceptions.InvalidPostException;
import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;
import com.bptn.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepository;

    public List<Post> getPostsByUsername(String username) {
    	
    	List<Post> posts = this.postRepository.findByUserId(new UserID(username));
    	
        posts = removeEmptyPosts(posts);
        
        return posts;
    }

    public Post getPostsByPostId(String postID){

    	Optional<Post> opt = this.postRepository.findById(postID);
    	
        if ( opt.isEmpty() ) {
            throw new InvalidPostException("PostID doesn't exist: " + postID);
        }
        
        return opt.orElse(null);
    }

    public List<Post> getPostsByPostType(String postType) throws InvalidPostException {

    	List<Post> posts = this.postRepository.findByPostType(postType);
    	
        posts = removeEmptyPosts(posts);
        
        return posts;
    }

    private List<Post> removeEmptyPosts(List<Post> posts) {
        
        posts.removeIf(p -> p.getPost()==null || 
        		            p.getPost().isEmpty());
        
        return posts;
    }

}
