package com.workpal.keypair.service;

import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.request.KeyPairCreateRequest;

public interface KeyPairService  {
	public String generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
	public void createKeyPair(KeyPairCreateRequest keyPairCreateRequest);
}
