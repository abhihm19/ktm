package com.osidigital.ktm.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;
	
	 public APIException(String message, HttpStatus status, String message1) {
	        super(message);
	        this.status = status;
	        this.message = message1;
	    }

}
