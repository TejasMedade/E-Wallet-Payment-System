/**
 * 
 */
package com.masai.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author tejas
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	// Customer Exception
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> CustomerExceptionHandler(CustomerException customerException,
			WebRequest req) {

		MyErrorDetails err = new MyErrorDetails();

		err.setTimestamp(LocalDateTime.now());
		err.setMessage(customerException.getMessage());
		err.setDescription(req.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}

	// Validation Exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMNVEHandler(MethodArgumentNotValidException me) {

		MyErrorDetails err = new MyErrorDetails();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage("Validation Error");
		err.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

	}

//	// Global Exception
////	@ExceptionHandler(Exception.class)
////	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception se, WebRequest req) {
////
////		MyErrorDetails err = new MyErrorDetails();
////		err.setTimestamp(LocalDateTime.now());
////		err.setMessage(se.getMessage());
////		err.setDescription(req.getDescription(false));
////
////		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
//
//	}

}
