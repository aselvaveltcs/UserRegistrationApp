package com.microservice.user.search.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRegistrationExceptionController {
	@ExceptionHandler(value = UserSearchGenericException.class)
	   public ResponseEntity<Object> exception(UserSearchGenericException exception) {
	      return new ResponseEntity<>("Error in User Search module. Error processing the request.", HttpStatus.BAD_REQUEST);
	   }
}

 