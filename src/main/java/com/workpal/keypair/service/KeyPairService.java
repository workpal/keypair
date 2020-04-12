package com.workpal.keypair.service;

import java.util.List;

import com.workpal.keypair.domain.KeyPair;
import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.request.KeyPairCreateRequest;

public interface KeyPairService  {
	public String generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
	public void createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
	public List<KeyPair> getAllKeyPairs();
	public KeyPair getKeyPairById(String keyPairId);
	public void removeKeyPairById(String keyPairId);
}
