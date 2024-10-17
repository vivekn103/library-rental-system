package com.te.brs.exception;

public class BookNotFoundException extends Exception {
	private static String message;
	public BookNotFoundException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
//	public void setMessage(String message) {
//		this.message = message;
//	}
	
}
