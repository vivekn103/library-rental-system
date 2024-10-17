package com.te.brs.exception;

public class DuplicateCustomerException extends Exception {
	private String message;
	public DuplicateCustomerException(String message) {
		super();
		this.message = message;
	}

}
