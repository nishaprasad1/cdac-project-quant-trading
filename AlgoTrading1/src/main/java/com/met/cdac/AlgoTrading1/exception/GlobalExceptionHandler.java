package com.met.cdac.AlgoTrading1.exception;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // this class represented as a exception handler by  spring boot
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)  // whenever this exception will come this method will get executed
	public ResponseEntity<ApiResponse>   resourceNotFoundExceptionHandler (ResourceNotFoundException ex)
	{
		String message=ex.getMessage();
		ApiResponse apiResponse= new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
		
	}
	

}
