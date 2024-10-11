package com.te.brs.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ResponseStructure<T> {
	private T data;
	private String message;
	private Boolean error;
	private int statusCode;
	
	public ResponseStructure<T> setData(T data) {
		this.data = data;
		return this;
	}
	public ResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public ResponseStructure<T> setError(Boolean error) {
		this.error = error;
		return this;
	}
	public ResponseStructure<T> setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		return this;
	}
	
	
}
