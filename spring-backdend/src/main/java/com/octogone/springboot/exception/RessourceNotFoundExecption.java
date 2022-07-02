package com.octogone.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RessourceNotFoundExecption extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public RessourceNotFoundExecption(String message){
		super(message);
	}
	
}
