package com.cookpick.controller;

import java.util.stream.Collectors;


import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cookpick.model.ErrorMessage;
import com.cookpick.model.ProductNotFoundException;


@RestControllerAdvice
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<String> exceptionHandler(Exception ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_GATEWAY);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler1(ProductNotFoundException ex){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.OK);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> validationException(MethodArgumentNotValidException ex){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(",")));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> validationException(ConstraintViolationException ex){
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(ex.getConstraintViolations().stream().map(x->x.getMessage()).collect(Collectors.joining(",")));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
