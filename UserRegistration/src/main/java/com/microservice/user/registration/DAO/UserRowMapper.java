package com.microservice.user.registration.DAO;

import org.springframework.jdbc.core.RowMapper; 
import com.microservice.user.model.UserRecord;

import java.sql.ResultSet;
import java.sql.SQLException; 

public class UserRowMapper implements RowMapper<UserRecord> {
	@Override
	   public UserRecord mapRow(ResultSet row, int rowNum) throws SQLException {
		UserRecord UserRec = new UserRecord();
		UserRec.setUserid(row.getString("LOGIN_ID"));
		UserRec.setPassword("");
		UserRec.setFirstname(row.getString("FIRST_NAME"));
		UserRec.setLastname(row.getString("LAST_NAME"));
		return UserRec;
	   }
}
