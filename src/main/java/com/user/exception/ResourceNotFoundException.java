package com.user.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
		super("Resources Not Found Exception !!" );
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
}
