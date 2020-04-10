package com.workpal.keypair.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.workpal.keypair.domain.KeyPair;

@Repository
public interface KeyPairRepository extends MongoRepository<KeyPair, String> {

}
