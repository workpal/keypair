package com.workpal.keypair.service;

import com.workpal.keypair.request.GenerateKeyPairRequest;

public interface KeyPairService  {
	public String generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest);
}
