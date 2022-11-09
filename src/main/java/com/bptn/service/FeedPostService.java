package com.bptn.service;

import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bptn.jpa.Post;
import com.bptn.jpa.UserID;
import com.bptn.repository.FeedPostRepository;
import com.bptn.request.FeedPostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FeedPostService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${api.key}")
	String apiKey;
	
	@Value("${api.base.url}")
	String apiBaseUrl;
		
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	FeedPostRepository feedPostRepository;
			
	/*
	{
	   "fromDate": "2022-10-13",
	   "toDate": "2022-11-07",
	   "queryKeyword": "newyork",
	   "username": "carlos"	
	}
	*/
	public Post getPostFormNewsAndSavePost(FeedPostRequest request) {
		
		
		/*
		 * 1. build the url of the request for newsapi
		 *    - buildAPIUrl() (9)
		 */
		String url = this.buildAPIUrl(request);
		
		/*
		 * 2. Send the request to the newsapi and receive the response
		 *    - getFeedFromNewsAPI(String URL) (7)
		 */
		String feedResult = getFeedFromNewsAPI(url);
		
		/*
		 * 3. Store the data received in step 2 in postgresql
		 *    - storeFeed() (8)
		 */
		
		Post feed = storeFeed(feedResult,request);
		
		return feed;
	}
	
	private String buildAPIUrl(FeedPostRequest request) {
		
		StringBuilder urlBuilder = new StringBuilder(); // Mutable - Not Thread Safe;
		
		ObjectMapper mapper = new ObjectMapper();
		
		//mapper.cre
		/*
		 * https://newsapi.org/v2/everything  
		 * ?q=  spring framework &
		 * from=2022-10-07 &
		 * sortBy=publishedAt & 
		 * apiKey=a31e59d25bff4ef2b252d5884a26ddfa
         */	
		urlBuilder.append(apiBaseUrl)
		          .append("?q=")
		          .append(request.getQueryKeyword());
		
		if ( request.getFromDate()!=null && !request.getFromDate().isEmpty()) {
			urlBuilder.append("&from=").append(request.getFromDate());
		}

		if ( request.getToDate()!=null && !request.getToDate().isEmpty()) {
			urlBuilder.append("&to=").append(request.getToDate());
		}
		
		urlBuilder.append("&apiKey=").append(apiKey);
	
		return urlBuilder.toString();
	}
	
	private String getFeedFromNewsAPI(String url) {
		
		String result = this.restTemplate.getForObject(url, String.class);
		
		return result;
	}
	
	private Post storeFeed(String feedResult, FeedPostRequest request) {

		UserID userId = new UserID(request.getUsername());
		
		Post feed = new Post();
		
		feed.setPost("hello");
		feed.setPostType(feedResult);
        feed.setUserId(userId);
        feed.setPostID(this.generatePostId(request));	
        
        logger.debug("Feed to be stored: {}", feed);
        
        return this.feedPostRepository.save(feed);
	}
	
	private String generatePostId(FeedPostRequest request) {
		
		Random random = new Random(System.currentTimeMillis());
		
		StringBuilder postIdBuilder = new StringBuilder();
		
		postIdBuilder.append(random.nextInt());
		postIdBuilder.append(Objects.hashCode(request.getUsername() + " " + request.getQueryKeyword()));
		
		String postId = postIdBuilder.toString();
		if (postId.startsWith("-")) {
			postId = postId.substring(1);
		}
		
		return postId;
	}
	
}
