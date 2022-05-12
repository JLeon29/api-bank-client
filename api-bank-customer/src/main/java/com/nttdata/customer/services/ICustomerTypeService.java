package com.nttdata.customer.services;

import com.nttdata.customer.models.CustomerType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerTypeService {
	
	public Flux<CustomerType> findAll();
	public Mono<CustomerType> findById(String id);
	public Mono<CustomerType> findByCode(String code);
	public Mono<CustomerType> create(CustomerType customerType);
	public Mono<Void> delete(CustomerType customerType);
	
	

}
