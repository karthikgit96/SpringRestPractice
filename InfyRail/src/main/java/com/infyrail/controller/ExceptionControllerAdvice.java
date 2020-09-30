package com.infyrail.controller;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infyrail.dto.ErrorMessage;
import com.infyrail.dto.NoSuchRouteException;
import com.infyrail.dto.RouteAlreadyPresentException;
import com.infyrail.util.InfyRailConstants;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@Autowired
	private Environment env;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex){
		ErrorMessage error = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),env.getProperty(InfyRailConstants.GENERAL_EXCEPTION_MESSAGE.toString()));
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.INTERNAL_SERVER_ERROR);		
	}
	
	@ExceptionHandler(RouteAlreadyPresentException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(RouteAlreadyPresentException ex){
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),env.getProperty(ex.getMessage()));
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(NoSuchRouteException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(NoSuchRouteException ex){
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),env.getProperty(ex.getMessage()));
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(MethodArgumentNotValidException ex){
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(",")));
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler(ConstraintViolationException ex){
		ErrorMessage error = new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getConstraintViolations().stream().map(x->x.getMessage()).collect(Collectors.joining(",")));
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
		
	}

}
