package com.te.brs.exception;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
public class BookOutOfStock extends Exception {
	private String message;
	
	public BookOutOfStock(String message) {
		super();
		this.message=message;
	}
}
