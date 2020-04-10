package com.workpal.keypair.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.workpal.keypair.request.GenerateKeyPairRequest;

public interface KeyPairApi {

	@PostMapping(value = "/keypair/generate", consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
}
