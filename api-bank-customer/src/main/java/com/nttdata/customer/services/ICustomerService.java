package com.nttdata.customer.services;

import com.nttdata.customer.models.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {
	
	public Flux<Customer> findAll();
	public Mono<Customer> findById(String id);
	public Mono<Customer> findByName(String name);
	public Mono<Customer> save(Customer customer);
	public Mono<Customer> update(String id, Customer customer);
	public Mono<Void> delete(Customer customer);
	public Mono<Customer> findByCustomerIdentityNumber(String customerIdentityNumber);
	public Mono<Customer> updateCard(String id, Customer customer);

}
