package com.workpal.keypair.api;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.request.KeyPairCreateRequest;
import com.workpal.keypair.request.KeyValidationRequest;
import com.workpal.keypair.service.KeyPairService;

@RestController()
@CrossOrigin
public class KeyPairApiController implements KeyPairApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairApiController.class);

	@Autowired
	private KeyPairService keyPairService;

	@Override
	public ResponseEntity<?> generateKeyPair(@Valid @RequestBody GenerateKeyPairRequest generateKeyPairRequest) {
		LOGGER.info("Generate key pair {} ", generateKeyPairRequest);
		var keyPair = keyPairService.generateKeyPair(generateKeyPairRequest);
		return new ResponseEntity<>(Map.of("privateKey", keyPair), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> createKeyPair(@Valid @RequestBody KeyPairCreateRequest keyPairCreateRequest) {
		LOGGER.info("Create key pair {} ", keyPairCreateRequest);
		keyPairService.createKeyPair(keyPairCreateRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> getAllKeyPair() {
		var keyPairs = keyPairService.getAllKeyPairs();
		return new ResponseEntity<>(keyPairs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getKeyPairById(@PathVariable(value = "keyPairId") String keyPairId) {
		LOGGER.info("Key pair Id {} ", keyPairId);
		var keyPair = keyPairService.getKeyPairById(keyPairId);
		return new ResponseEntity<>(keyPair, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> removeKeyPairById(String keyPairId) {
		LOGGER.info("Key pair Id {} ", keyPairId);
		keyPairService.removeKeyPairById(keyPairId);
		return new ResponseEntity<>(Map.of("message", "Keypair deleted successfully"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> ValidatePublicKey(KeyValidationRequest keyValidationRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
