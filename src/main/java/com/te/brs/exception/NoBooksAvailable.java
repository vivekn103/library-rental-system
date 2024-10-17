package com.te.brs.exception;

public class NoBooksAvailable extends Exception {
	String message;
	public NoBooksAvailable(String message){
		super();
		this.message=message;
	}
}
