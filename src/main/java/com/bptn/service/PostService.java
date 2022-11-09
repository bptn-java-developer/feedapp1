package com.bptn.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bptn.exceptions.InvalidPostException;
import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;
import com.bptn.repository.PostRepository;
import com.bptn.request.PostRequest;

@Service
public class PostService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.base.url}")
    private String apiBaseUrl;
    
    @Autowired
    RestTemplate restTemplate;
    
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

    public Post getPostFromNewsAndSavePost(PostRequest postRequest) {
    	
    	String url = this.buildApiUrl(postRequest);

    	logger.debug("News Feed URL = {}", url);

        String feedResult = this.getFeedFromNewsAPI(url);
        
        return storeFeed(feedResult, postRequest);
    }

    private String buildApiUrl(PostRequest postRequest) {
    	
    	StringBuilder urlBuilder = new StringBuilder();
    	
    	urlBuilder.append(apiBaseUrl);
    	
    	urlBuilder.append("?q=").append(postRequest.getQueryKeyword());
    	if (postRequest.getFromDate() != null && !postRequest.getFromDate().isEmpty()) {
    		urlBuilder.append("&from=").append(postRequest.getFromDate());
    	}
    	if (postRequest.getToDate() != null && !postRequest.getToDate().isEmpty()) {
    		urlBuilder.append("&to=").append(postRequest.getToDate());
    	}
    	urlBuilder.append("&apiKey=").append(apiKey);
    	
    	return urlBuilder.toString();
    }

    private String getFeedFromNewsAPI(String url) {
    	String result = this.restTemplate.getForObject(url, String.class);
    	/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(result);
        String prettyJsonString = gson.toJson(jsonElement);*/
    	return result;
    }

    private Post storeFeed(String feedResult, PostRequest postRequest) {
    	
    	UserID userId = new UserID(postRequest.getUsername());

    	Post feed = new Post();

        feed.setPost(feedResult);
        feed.setPostType(postRequest.getQueryKeyword());
        feed.setUserId(userId);
        feed.setPostID(this.generatePostId(postRequest));
        
        logger.debug("Feed to be Stored : {}", feed);
        
        return this.postRepository.save(feed);
    }

    private String generatePostId(PostRequest postRequest) {

    	Random random = new Random(System.currentTimeMillis());
        
    	StringBuilder postIdBuilder = new StringBuilder();
        postIdBuilder.append(random.nextInt());
        postIdBuilder.append(Objects.hashCode(postRequest.getUsername() + " " + postRequest.getQueryKeyword()));
        
        String postId = postIdBuilder.toString();
        if (postId.startsWith("-")) {
            return postId.substring(1);
        }

        return postId;
    }
}
