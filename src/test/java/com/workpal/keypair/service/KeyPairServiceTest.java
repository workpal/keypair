package com.workpal.keypair.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.service.impl.KeyPairServiceImpl;

@SpringBootTest
public class KeyPairServiceTest {

	@InjectMocks
	private KeyPairService keyPairService = new KeyPairServiceImpl();

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@DisplayName("testGenerateKeyPair")
	@Test
	public void testGenerateKeyPair() {
		var generateKeyPairRequest = new GenerateKeyPairRequest();
		generateKeyPairRequest.setName("Generate key pair test");
		generateKeyPairRequest.setDescription("Generate key pair test description");
		var privateKey = keyPairService.generateKeyPair(generateKeyPairRequest);
		
	}
}
