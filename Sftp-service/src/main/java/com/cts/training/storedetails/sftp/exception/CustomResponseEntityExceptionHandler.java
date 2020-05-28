package com.cts.training.storedetails.sftp.exception;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(Exception ex, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IncorrectResultSizeDataAccessException.class)
	public final ResponseEntity<Object> handleIncorrectResultSizeDataAccessException(Exception ex, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(), "No user found with given Username and Password", request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public final ResponseEntity<Object> handleEmptyResultDataAccessException(Exception ex, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(), "No user found with given Username and Password", request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<Object> handleHttpClientErrorException(Exception ex, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(),
				ex.getLocalizedMessage().split("\"")[7], request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServerDownException.class)
	public final ResponseEntity<Object> handleServerDownException(Exception ex, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Handling Already Handled Exceptions
	
	@Override
	public final ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(), "U Need to pass the Body with POST Method", request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		ExceptionResponseDetails response = new ExceptionResponseDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(response,HttpStatus.METHOD_NOT_ALLOWED);
	}
	
}


