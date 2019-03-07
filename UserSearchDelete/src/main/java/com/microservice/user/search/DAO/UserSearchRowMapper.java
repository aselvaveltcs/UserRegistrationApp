package com.microservice.user.search.DAO;

import org.springframework.jdbc.core.RowMapper; 
import java.sql.ResultSet;
import java.sql.SQLException; 

import com.microservice.user.search.model.UserSearchRecord;

public class UserSearchRowMapper implements RowMapper<UserSearchRecord>  {
	
	@Override
	   public UserSearchRecord mapRow(ResultSet row, int rowNum) throws SQLException {
		UserSearchRecord UserRec = new UserSearchRecord();
		UserRec.setUserid(row.getString("LOGIN_ID"));
		return UserRec;
	   }
}
 
