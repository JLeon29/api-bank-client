package com.nttdata.customer.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.customer.models.Customer;
import com.nttdata.customer.repositories.CustomerRepository;
import com.nttdata.customer.services.ICustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	private static final Logger log  = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public Flux<Customer> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Customer> findById(String id) {
		return repository.findById(id);
    }

	@Override
	public Mono<Customer> findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Customer> save(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Customer> update(String id, Customer customer) {
		return repository.findById(id)
                .flatMap(existCustomer ->{
                    existCustomer.setName(customer.getName());
                    existCustomer.setCustomerIdentityNumber(customer.getCustomerIdentityNumber());
                    existCustomer.setCustomerType(customer.getCustomerType());
                    return repository.save(existCustomer);
                });
    }

	@Override
	public Mono<Void> delete(Customer customer) {
		return repository.delete(customer);
    }

	@Override
	public Mono<Customer> findByCustomerIdentityNumber(String customerIdentityNumber) {
		return repository.findByCustomerIdentityNumber(customerIdentityNumber)
                .switchIfEmpty(Mono.just(Customer.builder()
                        .customerIdentityNumber(null).build()));
    }

	@Override
	public Mono<Customer> updateCard(String id, Customer customer) {
		return repository.findById(id)
                .flatMap(existCustomer -> {
                    log.info("CUSTOMER Identity Number: {}", customer.getCustomerIdentityNumber());
                    return repository.save(existCustomer)
                            .doOnNext(c -> log.info("Customer Response: Customer={}", c.getName()));
                });
    }

}
