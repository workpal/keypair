package com.workpal.keypair.service.impl;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.JSch;
import com.workpal.keypair.domain.KeyPair;
import com.workpal.keypair.enums.KeyCreationType;
import com.workpal.keypair.exception.InternalServerErrorException;
import com.workpal.keypair.repository.KeyPairRepository;
import com.workpal.keypair.request.GenerateKeyPairRequest;
import com.workpal.keypair.service.KeyPairService;

@Service
public class KeyPairServiceImpl implements KeyPairService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyPairServiceImpl.class);
	
	@Autowired
	private KeyPairRepository keyPairRepository;

	@Override
	public String generateKeyPair(GenerateKeyPairRequest generateKeyPairRequest) {
		var keyPairMap = generateKeyPair();
		String publicKey = keyPairMap.get("publicKey");
		String privateKey = keyPairMap.get("privateKey");
		var keyPair = new KeyPair(generateKeyPairRequest.getName(), generateKeyPairRequest.getDescription(), publicKey,
				KeyCreationType.GENERATED);
		LOGGER.info("Generate keypair {} ", keyPair);
		keyPairRepository.save(keyPair);
		return privateKey;
	}
	
	private Map<String, String> generateKeyPair() {
		Map<String,String> keyPairMap = new HashMap<String, String>();
		String publicKeyContent = null;
		try {
			String comment = "ci-key-" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			JSch jsch = new JSch();

			com.jcraft.jsch.KeyPair kpair = com.jcraft.jsch.KeyPair.genKeyPair(jsch, com.jcraft.jsch.KeyPair.RSA, 2048);

			byte[] pubblob = kpair.getPublicKeyBlob();
			byte[] pub = Base64.getEncoder().encode(pubblob);
			
			 ByteArrayOutputStream privateKeyBuff = new ByteArrayOutputStream(2048);
			 kpair.writePrivateKey(privateKeyBuff);

			publicKeyContent = new String(pub, StandardCharsets.UTF_8);
			publicKeyContent = "ssh-rsa " + publicKeyContent + " " + comment;
			keyPairMap.put("privateKey", privateKeyBuff.toString());
			keyPairMap.put("publicKey", publicKeyContent);
			kpair.dispose();

		} catch (Exception e) {
			LOGGER.error("Error in keypair generation {}", e);
			throw new InternalServerErrorException("Internal Server Error");
		}

		return keyPairMap;
	}

}
