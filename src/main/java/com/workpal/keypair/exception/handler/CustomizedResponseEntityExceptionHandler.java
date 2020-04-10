package com.workpal.keypair.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.workpal.keypair.exception.InternalServerErrorException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler  {
	@ExceptionHandler(InternalServerErrorException.class)
	ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException ex) {
		System.err.println("Error : " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
}
