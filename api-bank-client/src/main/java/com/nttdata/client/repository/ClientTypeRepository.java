package com.nttdata.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.client.model.ClientType;

import reactor.core.publisher.Mono;

@Repository
public interface ClientTypeRepository extends ReactiveMongoRepository<ClientType, String> {
	
	Mono<ClientType> findByCode(String code);

}
