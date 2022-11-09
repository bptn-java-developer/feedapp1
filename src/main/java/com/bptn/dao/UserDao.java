package com.bptn.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bptn.bean.UserID;
import com.bptn.dao.mapper.UserMapper;

@Repository
public class UserDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<UserID> listUsers() {

		String sql = "SELECT * FROM \"UserID\"";

		return jdbcTemplate.query(sql, new UserMapper());
	}
	
	public UserID findByUsername(String username) {

		String sql = "SELECT * FROM \"UserID\" WHERE username = ?";

		List<UserID> users = jdbcTemplate.query(sql, new UserMapper(), username);

		return users.isEmpty()?null:users.get(0);
	}
	
	public void createUser(UserID user) {
		
        String sql = "INSERT INTO \"UserID\"(username,name,\"emailID\",\"phoneNumber\",\"userPassword\") " +
                     "VALUES(?,?,?,?,?)";
	
        logger.debug("Insert Query: {}",sql);
        
        jdbcTemplate.update(sql,new Object[] {user.getUsername(), user.getName(),
                user.getEmailID(), user.getPhoneNumber(), user.getUserPassword()});
	}
}
