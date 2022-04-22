package com.nttdata.client.service;

import com.nttdata.client.model.ClientType;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface IClientTypeService {
	
	Flux<ClientType> findAll();

    Mono<ClientType> findById(String id);

    Mono<ClientType> findByCode(String code);

    Mono<ClientType> create(ClientType clientType);

    Mono<ClientType> update(ClientType clientType);

    Mono<Void> delete(ClientType customerType);

}