package com.springboot.exception.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import com.springboot.exception.bean.CustomException
import com.springboot.exception.bean.ErrorResponse

/**
 * Class to handle Global Exception at one place
 * 
 * @author Pratik
 */
@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * This method will capture All Exception thrown in application
     * 
     * @param ex Exception object containing details of the exception thrown
     * @param request Contains request metadata
     * @return Returns ResponseEntity with ErrorResponse
     */
    @ExceptionHandler(Exception)
    ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        // Create a custom error response body
        def errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message,
            request.getDescription(false)
        )

        new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    /**
     * This method handles NullPointerExceptions
     * 
     * @param nullPointerException Exception Object which contains exception message and details
     * @param request Contains request metadata
     * @return Returns details where the Null Pointer exception occurred
     */
    @ExceptionHandler(NullPointerException)
    ResponseEntity<Object> handleNullPointerException(NullPointerException nullPointerException, WebRequest request) {
        def errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Null pointer occurred: ${nullPointerException.message}",
            request.getDescription(false)
        )
        new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST)
    }
	
	/**
	 * When any validation is violated this will capture MethordArgumentNotValidException and generate Validation error reposonse
	 * 
	 * @param methodValidationException Exception Object which contains field and validation message 
	 * @param request Contains request metadata
	 * @return It returns Response Entity containing Error message and Validation Error list with validation messages
	 */
	@ExceptionHandler(MethodArgumentNotValidException)
	ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException methodValidationException, WebRequest request) {
		 Map<String, String> errors = new HashMap<>();

        methodValidationException.getBindingResult().getFieldErrors().each{ error ->
            String fieldName = error.field;  // Get the field name
            String errorMessage = error.defaultMessage;  // Get the error message
            errors.put(fieldName, errorMessage);  // Add to the error map
        }
        return ResponseEntity.badRequest().body([message: "Validation failed", errors: (errors)])
	}

    /**
     * This will handle any Custom Exception if defined in the project. You can return custom status code, message and Details of exception with it.
     * @param customException Custom Exception Object which contains status code, message and details of the exception
     * @param request Contains request metadata
     * @return Returns ResponseEntity with ErrorResponse object and appropriate HTTP status code
     */
    @ExceptionHandler(CustomException)
    ResponseEntity<Object> handleCustomException(CustomException customException, WebRequest request) {
        def errorResponse = new ErrorResponse(
            customException.getStatusCode(),
            customException.message,
            request.getDescription(false)
        )
        new ResponseEntity<>(errorResponse, HttpStatus.valueOf(customException.getStatusCode()))
    }
}
