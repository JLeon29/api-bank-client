package com.nttdata.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;
import com.nttdata.client.repository.ClientRepository;
import com.nttdata.client.service.IClientService;
import com.nttdata.client.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Mono<Client> save(Client client) {
		return clientRepository.save(client);
	
	}

	@Override
	public Flux<ClientDto> listClients() {
		return clientRepository.findAll().map(AppUtils::entityToDto);
	}

	@Override
	public Mono<ClientDto> findClientById(String id) {
		return clientRepository.findById(id).map(AppUtils::entityToDto);
	}

	@Override
	public Mono<ClientDto> finClientByName(String name) {
		return clientRepository.findByName(name);
	}

	@Override
	public Mono<ClientDto> finClientByClientIdNumber(String clientNumber) {
		return clientRepository.findByClientIdNumber(clientNumber).switchIfEmpty(Mono.just(ClientDto.builder()
                .clientIdNumber(null).build()));
	}

	@Override
	public Mono<ClientDto> saveClients(Mono<ClientDto> clientDtoMono) {
		return clientDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(clientRepository::insert)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<ClientDto> updateClients(Mono<ClientDto> clientDtoMono, String id) {
		return clientRepository.findById(id)
                .flatMap(p->clientDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(clientRepository::save)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<Void> deleteClient(String id) {
		return clientRepository.deleteById(id);
	}
	
	
	

	/*
	@Override
	public Mono<Client> save(Client client) {
		
		return clientRepository.save(client);
	}

	@Override
	public Flux<Client> getClients() {
		
		return clientRepository.findAll();
	}

	@Override
	public Mono<Client> getClientById(String id) {
		
		return clientRepository.findById(id);
	}

	@Override
	public Mono<Client> getClientByName(String name) {
		
		return clientRepository.findByName(name);
	}

	@Override
	public Mono<Client> getClientByClientIdNumber(String clientNumber) {
		
		return clientRepository.findByClientIdNumber(clientNumber);
	}

	@Override
	public Mono<Client> updateClient(Client client) {
		
		return clientRepository.save(client);
	}

	@Override
	public Mono<Void> deleteClient(Client client) {
		
		return clientRepository.delete(client);
	}
	*/

	
}
