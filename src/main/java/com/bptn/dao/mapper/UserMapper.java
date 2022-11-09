package com.bptn.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bptn.bean.UserID;

public class UserMapper implements RowMapper<UserID> {

	@Override
	public UserID mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserID userId = new UserID();

		userId.setUsername(rs.getString("username"));
		userId.setName(rs.getString("name"));
		userId.setEmailID(rs.getString("emailID"));
		userId.setPhoneNumber(rs.getString("phoneNumber"));
		userId.setUserPassword(rs.getString("userPassword"));
		
		return userId;
	}
}
