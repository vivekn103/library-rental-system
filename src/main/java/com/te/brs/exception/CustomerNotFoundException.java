package com.te.brs.exception;

public class CustomerNotFoundException extends Exception {
	private String message;
	public CustomerNotFoundException(String message) {
		super();
		this.message = message;
	}
}
