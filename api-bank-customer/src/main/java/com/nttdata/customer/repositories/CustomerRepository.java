package com.nttdata.customer.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.customer.models.Customer;

import reactor.core.publisher.Mono;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	
	Mono<Customer> findByName(String name);
    Mono<Customer> findByCustomerIdentityNumber(String customerIdentityNumber);

}
