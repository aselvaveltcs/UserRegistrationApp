package com.microservice.user.registration.DAO;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.microservice.user.model.UserRecord;


@Repository
public class UserRegistrationDAOImplementation implements UserRegistrationDAO {
	
	int StatusCode=-999;
	int insert_status_code_1 = -999;
	int insert_status_code_2 = -999;
	int final_insert_status_code = -999;
	
	private final String INSERT_SQL = "INSERT INTO USER_REGISTERED(LOGIN_ID, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?)";
	private final String INSERT_SQL_2 = "INSERT INTO USER_LOGIN(LOGIN_ID, PASSWORD) VALUES (?,?)";
	private final String SELECT_ALL_SQL = "SELECT * FROM  USER_REGISTERED" ;
	
	
	@Autowired JdbcTemplate jdbcTemplate;

	@Override
   public int createUsers(String UserId, String UserFirstName, String UserLastName, String UserPassword)  {
		insert_status_code_1 =   jdbcTemplate.update(INSERT_SQL,UserId, UserFirstName, UserLastName);
		insert_status_code_2 = jdbcTemplate.update(INSERT_SQL_2,UserId, UserPassword);
		
		if (insert_status_code_1 >=1 || insert_status_code_2 >= 1 )
			final_insert_status_code = 1;
			
		return final_insert_status_code;
		
	}

	@Override
	public List<UserRecord> getAllUsers()   {
		RowMapper<UserRecord> rowMapper = new UserRowMapper();
		   return this.jdbcTemplate.query(SELECT_ALL_SQL, rowMapper);
	}

	@Override
	public int updateUser(String UserId, String UserFirstName, String UserLastName) {
		if (!UserFirstName.isEmpty() && !UserLastName.isEmpty() &&  !UserId.isEmpty() )
		{
			String UPDATE_SQL = "UPDATE USER_REGISTERED SET FIRST_NAME=?, LAST_NAME=? WHERE LOGIN_ID=?";
			StatusCode = jdbcTemplate.update(UPDATE_SQL, UserFirstName, UserLastName, UserId);
		}
		
		if (!UserFirstName.isEmpty() && UserLastName.isEmpty() &&  !UserId.isEmpty() )
		{
			String UPDATE_SQL = "UPDATE USER_REGISTERED SET FIRST_NAME=? WHERE LOGIN_ID=?";
			StatusCode = jdbcTemplate.update(UPDATE_SQL, UserFirstName, UserId);
		}
		
		if (UserFirstName.isEmpty() && !UserLastName.isEmpty() &&  !UserId.isEmpty() )
		{
			String UPDATE_SQL = "UPDATE USER_REGISTERED SET LAST_NAME=? WHERE LOGIN_ID=?";
			StatusCode = jdbcTemplate.update(UPDATE_SQL, UserLastName, UserId);
		}
		
		return StatusCode;
	}

}
