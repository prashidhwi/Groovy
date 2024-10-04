package com.springboot.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import com.springboot.exception.bean.CustomException
import com.springboot.exception.bean.ErrorResponse

@ControllerAdvice
class GlobalExceptionHandler {

    // Catch any exception globally
    @ExceptionHandler(Exception)
    ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // Create a custom error response body
        def errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message,
            request.getDescription(false)
        )

        // Return a response entity with the error details and HTTP status
        new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    // You can also handle specific exceptions here
    @ExceptionHandler(NullPointerException)
    ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        def errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Null pointer occurred: ${ex.message}",
            request.getDescription(false)
        )
        new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST)
    }

    // Handle custom exceptions, if needed
    @ExceptionHandler(CustomException)
    ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
        def errorResponse = new ErrorResponse(
            ex.getStatusCode(),
            ex.message,
            request.getDescription(false)
        )
        new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()))
    }
}
