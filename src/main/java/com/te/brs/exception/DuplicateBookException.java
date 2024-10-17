package com.te.brs.exception;

public class DuplicateBookException extends Exception {
	private String message;
	public DuplicateBookException(String message) {
		super();
		this.message = message;
	}
}
