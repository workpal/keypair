package com.workpal.keypair.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.workpal.keypair.domain.KeyPair;
import com.workpal.keypair.enums.KeyCreationType;
import com.workpal.keypair.repository.KeyPairRepository;
import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.request.KeyPairCreateRequest;
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
	
	@DisplayName("testCreateKeyPair")
	@Test
	void testCreateKeyPair() {
		var keyPairCreateRequest = new KeyPairCreateRequest();
		keyPairCreateRequest.setName("Create key pair test");
		keyPairCreateRequest.setDescription("Create key pair test description");
		keyPairCreateRequest.setKey(
				"ssh-rsa AAAAB3NzaC1yc2EAAAABJQAAAH4A2KFIgLhQNmR82VQn5zGzHURA03JVzNL/U3P19hpIv3CQr1PETAj0e30/mid2ESwoYgSsYyKusD0DEeqG9joYzfdMO14zbd6nJNwmd38C+ltEI4mG2K+/7MZdJO6WXpfs6citVSy2tB6hucXemUqn441ghO88SxjrxrOCtU8=");

		when(keyPairRepo.save(any(KeyPair.class))).thenReturn(new KeyPair());
		keyPairService.createKeyPair(keyPairCreateRequest);

	}
	@DisplayName("testGetAllKeyPair")
	@Test
	public void testGetAllKeyPair() {
		var keyPair = new KeyPair("keypair name", "keypair description", "ssh-rsa asdas", KeyCreationType.IMPORTED);
		List<KeyPair> keyPairList = List.of(keyPair);
		when(keyPairRepo.findAll()).thenReturn(keyPairList);
		var keyPairs = keyPairService.getAllKeyPairs();
		assertEquals(keyPair, keyPairs.get(0));
		assertTrue(keyPairs.size() ==0);
	}	
}
