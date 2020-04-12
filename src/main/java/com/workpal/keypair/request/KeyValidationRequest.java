package com.workpal.keypair.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class KeyValidationRequest {

	@NotEmpty(message="Public Key is mandatory")
	private String key; 
}
