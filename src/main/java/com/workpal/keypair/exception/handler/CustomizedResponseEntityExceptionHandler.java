package com.workpal.keypair.exception.handler;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.workpal.keypair.dto.Error;

import com.workpal.keypair.dto.ErrorResponse;
import com.workpal.keypair.enums.ErrorCode;
import com.workpal.keypair.exception.InternalServerErrorException;
import com.workpal.keypair.exception.KeyPairValidationException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
	@ExceptionHandler(InternalServerErrorException.class)
	ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException ex) {
		System.err.println("Error : " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}

	@ExceptionHandler(KeyPairValidationException.class)
	ResponseEntity<?> handleKeyPairValidationException(KeyPairValidationException ex) {
		var errors = new ArrayList<Error>();
		var errInfoMap = new HashMap<String, Object>();
		errInfoMap.put("name", ex.getMessage());
		var error = Error.builder().errorCode(ErrorCode.NOT_VALID_KEY).errorInfo(errInfoMap).build();
		errors.add(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ErrorResponse.builder().errorMessages(errors).build());
	}
}
