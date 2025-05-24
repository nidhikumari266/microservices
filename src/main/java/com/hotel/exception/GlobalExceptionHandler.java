package com.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.user.payload.ApiResponse;

public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		ApiResponse response = ApiResponse.builder()
	            .message(ex.getMessage())
	            .success(false)
	            .status(HttpStatus.NOT_FOUND)
	            .build();

	    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

}
