package com.nttdata.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.client.model.ClientType;
import com.nttdata.client.repository.ClientTypeRepository;
import com.nttdata.client.service.IClientTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientTypeServiceImpl implements IClientTypeService {
	
	@Autowired
	private ClientTypeRepository clientTypeRepository;

	@Override
	public Flux<ClientType> findAll() {
		
		return clientTypeRepository.findAll();
	}

	@Override
	public Mono<ClientType> findById(String id) {
		
		return clientTypeRepository.findById(id);
	}

	@Override
	public Mono<ClientType> findByCode(String code) {
		
		return clientTypeRepository.findByCode(code);
	}

	@Override
	public Mono<ClientType> create(ClientType clientType) {
		
		return clientTypeRepository.save(clientType);
	}

	@Override
	public Mono<Void> delete(ClientType clientType) {
		
		return clientTypeRepository.delete(clientType);
	}

	@Override
	public Mono<ClientType> update(ClientType clientType) {
		
		return clientTypeRepository.save(clientType);
	}

}
