package com.te.brs.exceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.brs.exception.BookNotFoundException;
import com.te.brs.exception.BookOutOfStock;
import com.te.brs.exception.CustomerNotFoundException;
import com.te.brs.exception.DuplicateBookException;
import com.te.brs.exception.DuplicateCustomerException;
import com.te.brs.exception.NoBooksAvailable;
import com.te.brs.responseEntity.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
//	private T data;
//	private String message;
//	private Boolean error;
//	private int statusCode;
	
	@ExceptionHandler(NoBooksAvailable.class)
	public ResponseEntity<ResponseStructure<Object>> noBooksException(NoBooksAvailable available){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().data(available.getMessage().toString()).message("There are no Books in Library at all").error(true).statusCode(HttpStatus.NOT_FOUND.value()).build());
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ResponseStructure<Object>> handlerBookNotFoundException(BookNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().data(e.getMessage().toString()).message("There are no Books in Library with the name you have mentioned").error(true).statusCode(HttpStatus.NOT_FOUND.value()).build());
	}
	
	@ExceptionHandler(BookOutOfStock.class)
	public ResponseEntity<ResponseStructure<Object>> handlerBookOutOfStock(BookOutOfStock e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().data(e.getMessage().toString()).message("Books are out of stock").error(true).statusCode(HttpStatus.NOT_FOUND.value()).build());
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<Object>> handlerCustomerNotFoundException(CustomerNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().data(e.getMessage().toString()).message("Dont Have Account With the email").error(true).statusCode(HttpStatus.NOT_FOUND.value()).build());
	}
	
	@ExceptionHandler(DuplicateBookException.class)
	public ResponseEntity<ResponseStructure<Object>> handlerDuplicateBookException(DuplicateBookException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().data(e.getMessage().toString()).message("You Have Already Purchased this book").error(true).statusCode(HttpStatus.NOT_FOUND.value()).build());
	}
	
	
	@ExceptionHandler(DuplicateCustomerException.class)
	public ResponseEntity<ResponseStructure<Object>> handlerDuplicateCustomerException(DuplicateCustomerException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().data(e.getMessage().toString()).message("You Have Already have a account with your email").error(true).statusCode(HttpStatus.NOT_FOUND.value()).build());
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseStructure<Map<String,String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		LinkedHashMap<String, String> map = new LinkedHashMap<String,String>();
		
		List<FieldError> fieldErrors = e.getFieldErrors();
		fieldErrors.stream().forEach(t -> map.put( t.getField(),t.getDefaultMessage()));
		
		ResponseStructure<Map<String,String>> responseStructure = new ResponseStructure<Map<String,String>>().setData(map).setMessage("You Have Entered Some Wrong Data").setError(true).setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
	}
	
	
}
