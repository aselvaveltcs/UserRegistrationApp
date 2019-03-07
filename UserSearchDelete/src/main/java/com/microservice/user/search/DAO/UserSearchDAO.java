

package com.microservice.user.search.DAO;


public interface UserSearchDAO {
	public abstract int deleteUser (String UserId);	
	public abstract boolean findUserById (String UserId);	
}

 