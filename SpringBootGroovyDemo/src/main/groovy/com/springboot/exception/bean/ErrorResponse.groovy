package com.springboot.exception.bean

import groovy.transform.TupleConstructor

@TupleConstructor
class ErrorResponse {
	int statusCode;
	String message;
	String details;
}
