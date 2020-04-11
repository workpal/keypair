package com.workpal.keypair.api;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.request.KeyPairCreateRequest;

public interface KeyPairApi {

	@PostMapping(value = "/keypair/generate", consumes = {MediaType.APPLICATION_JSON_VALUE},  produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
	
	@PostMapping(value = "/keypair/create", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	
	@GetMapping(value = "/keypair", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> getAllKeyPair();

}
