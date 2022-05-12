package com.nttdata.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.customer.models.CustomerType;
import com.nttdata.customer.repositories.CustomerTypeRepository;
import com.nttdata.customer.services.ICustomerTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerTypeServiceImpl implements ICustomerTypeService {
	
	@Autowired
	private CustomerTypeRepository repository;

	@Override
	public Flux<CustomerType> findAll() {
		return repository.findAll();
    }

	@Override
	public Mono<CustomerType> findById(String id) {
		return repository.findById(id);
    }

	@Override
	public Mono<CustomerType> findByCode(String code) {
		return repository.findByCode(code);
    }

	@Override
	public Mono<CustomerType> create(CustomerType customerType) {
		return repository.save(customerType);
    }

	@Override
	public Mono<Void> delete(CustomerType customerType) {
		return repository.delete(customerType);
    }

}
