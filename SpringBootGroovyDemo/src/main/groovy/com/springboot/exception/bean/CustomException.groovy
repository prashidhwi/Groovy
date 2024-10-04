package com.springboot.exception.bean

class CustomException extends RuntimeException {
	int statusCode
	
	CustomException(String message, int statusCode) {
		super(message)
		this.statusCode = statusCode
	}
}
