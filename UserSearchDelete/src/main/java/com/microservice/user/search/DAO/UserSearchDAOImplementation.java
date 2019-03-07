package com.microservice.user.search.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.microservice.user.search.model.UserSearchRecord;

@Repository
public class UserSearchDAOImplementation implements UserSearchDAO {

	
	private final String DELETE_SQL_1 = "DELETE FROM USER_LOGIN WHERE LOGIN_ID=?";
	private final String DELETE_SQL_2 = "DELETE FROM USER_REGISTERED WHERE LOGIN_ID=?";
	private final String SELECT_SQL = "SELECT LOGIN_ID FROM USER_LOGIN WHERE LOGIN_ID=?";
	int Delete_Status_Code_1 = -999;
	int Delete_Status_Code_2 = -999;
	int Final_Status_Code = -999;
	
	@Autowired JdbcTemplate jdbcTemplate;
	
	@Override
	public int deleteUser(String UserId) {
		
		if (findUserById(UserId))
		{		
			Delete_Status_Code_1 =  jdbcTemplate.update(DELETE_SQL_1,UserId);
			Delete_Status_Code_2 =  jdbcTemplate.update(DELETE_SQL_2,UserId);
			if (Delete_Status_Code_1 >=1 || Delete_Status_Code_2 >= 1 )
			Final_Status_Code = 1;	
		}
		
		return Final_Status_Code;
	}

	@Override
	public boolean findUserById(String UserId) {
		try
		{
		
		RowMapper<UserSearchRecord> rowMapper = new UserSearchRowMapper();
		UserSearchRecord FoundUser = jdbcTemplate.queryForObject(SELECT_SQL, rowMapper,UserId);
		
		if ( !isNullOrEmpty(FoundUser.getUserid()) )
			return true;
		else
			return false;
		
		}
		
		catch (Exception e)
		{
			return false;
		}
	}
	
	
	 public static boolean isNullOrEmpty(String str) {
	        if(str != null && !str.isEmpty())
	            return false;
	        return true;
	    }

}
