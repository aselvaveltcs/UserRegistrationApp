package com.microservice.user.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.user.search.DAO.UserSearchDAOImplementation;
import com.microservice.user.search.exception.UserSearchGenericException;
import com.microservice.user.search.model.UserSearchRecord;

 


 

 
@RestController
public class UserSearchDeleteController {

	@Autowired UserSearchDAOImplementation userSearchDAO;
	@Autowired
	private Environment env;
	
	// REST API Calling Method: PUT
		// http://localhost:8081/DeleteUserUri?userid=1
		
		   @RequestMapping(value = "/DeleteUserUri",  method = RequestMethod.PUT)
		   public ResponseEntity<Object> deleteUserUri_1(@RequestParam("userid") String UserId) {	
			   
			   if(isNullOrEmpty(UserId)) return new ResponseEntity<>("User Id cannot be blank.", HttpStatus.BAD_REQUEST); 
			  
			   
			   if(userSearchDAO.deleteUser(UserId) >= 1){
				      return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
		        }else{
		        	throw new UserSearchGenericException();
		        }
		   }
		
			// REST API Calling Method: PUT
			// http://localhost:8081/DeleteUser/1
		   
		   @RequestMapping(value = "/DeleteUser/{userid}" , method = RequestMethod.PUT)
		   public ResponseEntity<Object> deleteUserUri_2(@PathVariable("userid") String UserId) {	
			   
			   
			   if(isNullOrEmpty(UserId)) return new ResponseEntity<>("User Id cannot be blank.", HttpStatus.BAD_REQUEST); 
			   
			   if(userSearchDAO.deleteUser(UserId) >= 1){
				      return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
		        }else{
		        	throw new UserSearchGenericException();
		        }
		   }
		   
			// REST API Calling Method: PUT
			// http://localhost:8081/DeleteUser
		   // In Msg Body:
		   // {"userid":"1"}
		   
		   @RequestMapping(value = "/DeleteUser", headers="Content-Type=application/json", method = RequestMethod.PUT)
		   public ResponseEntity<Object> deleteUser(@RequestBody UserSearchRecord userRecBody ) {	
			   
			   String UserId = userRecBody.getUserid();
			   
			   if(isNullOrEmpty(UserId)) return new ResponseEntity<>("User Id cannot be blank.", HttpStatus.BAD_REQUEST); 
			   
			   if(userSearchDAO.deleteUser(UserId) >= 1){
				      return new ResponseEntity<>("User has been deleted successfully", HttpStatus.OK);
		        }else{
		        	throw new UserSearchGenericException();
		        }
		   }
	   
 
	   //Only used for internal microservice to microservice calling
		// REST API Calling Method: PUT
		// http://localhost:8081/CallDeleteUser/1
	   
	   @RequestMapping(value = "/CallDeleteUser/{userid}" , method = RequestMethod.GET)
	   public int deleteUser_called(@PathVariable("userid") String UserId) {
		   
		   System.out.println("Within User UserSearchDelete.CallDeleteUser. UserId:" + UserId); 
		   
		try
		{
			   if(isNullOrEmpty(UserId)) return 999 ;
			   
			   if(userSearchDAO.deleteUser(UserId) >= 1){
				   System.out.println("Deleted User Id: UserSearchDelete.CallDeleteUser. UserId:" + UserId); 
				      return  200;
		        }else{
		        	return  999;
		        }
		}
		catch (Exception e)
		{
			return  999;
		}

	   }
	   

	   
		// REST API Calling Method: GET
		// http://localhost:8081/FindUser/1
	   
	   @RequestMapping(value = "/FindUser/{userid}", method = RequestMethod.GET)
	   public ResponseEntity<Object> FindUser(@PathVariable("userid") String UserId) { 

		   
		   if(isNullOrEmpty(UserId)) return new ResponseEntity<>("User Id cannot be blank.", HttpStatus.BAD_REQUEST); 
		   
		   if(userSearchDAO.findUserById(UserId)){
			      return new ResponseEntity<>("User found.", HttpStatus.FOUND);
	        }else{
	        	  return new ResponseEntity<>("User Not found.", HttpStatus.NOT_FOUND);
	        }
	   }
	   
	   //Only used for internal microservice to microservice calling
		// REST API Calling Method: GET
		// http://localhost:8081/SearchUser/1
	   
	   @RequestMapping(value = "/SearchUser/{userid}", method = RequestMethod.GET)
	   public boolean SearchUser(@PathVariable("userid") String UserId) { 
		   
		   System.out.println(  "Hello from UserSearchDelete Service running at port: " + env.getProperty("local.server.port"));
		   
		   if(userSearchDAO.findUserById(UserId)){
			      return true;
	        }else{
	        	  return false;
	        }
	   }
	   
	   
		// REST API Calling Method: GET
		// http://localhost:8081/FindUserUri?userid=1
	   
	   @RequestMapping(value = "/FindUserUri",  method = RequestMethod.GET)
	   public ResponseEntity<Object> FindUserUri(@RequestParam("userid") String UserId ) { 
		   
		   if(isNullOrEmpty(UserId)) return new ResponseEntity<>("User Id cannot be blank.", HttpStatus.BAD_REQUEST); 
		   
		   if(userSearchDAO.findUserById(UserId)){
			      return new ResponseEntity<>("User found.", HttpStatus.FOUND);
	        }else{
	        	  return new ResponseEntity<>("User Not found.", HttpStatus.NOT_FOUND);
	        }
	   }
	   	   

	   public static boolean isNullOrEmpty(String str) {
	        if(str != null && !str.isEmpty())
	            return false;
	        return true;
	    }
}
