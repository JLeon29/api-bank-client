package com.nttdata.customer.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.customer.models.CustomerType;

import reactor.core.publisher.Mono;

@Repository
public interface CustomerTypeRepository extends ReactiveMongoRepository<CustomerType, String> {
	
	 Mono<CustomerType> findByCode(String code);

}
