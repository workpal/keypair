package com.workpal.keypair.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.workpal.keypair.domain.KeyPair;
import com.workpal.keypair.repository.KeyPairRepository;
import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.service.impl.KeyPairServiceImpl;

@SpringBootTest
public class KeyPairServiceTest {

	@Mock
	private KeyPairRepository keyPairRepo;

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
		when(keyPairRepo.save((any(KeyPair.class)))).thenReturn(new KeyPair());
		var privateKey = keyPairService.generateKeyPair(generateKeyPairRequest);
		assertTrue(!privateKey.isEmpty());

	}
}
