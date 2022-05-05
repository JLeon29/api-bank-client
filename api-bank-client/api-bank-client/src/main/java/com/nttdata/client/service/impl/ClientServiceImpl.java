package com.nttdata.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.ClientType;
import com.nttdata.client.model.dto.ClientDto;
import com.nttdata.client.repository.ClientRepository;
import com.nttdata.client.repository.ClientTypeRepository;
import com.nttdata.client.service.IClientService;
import com.nttdata.client.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ClientTypeRepository clientTypeRepository;
	

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

	/*
	@Override
	public Mono<Client> finClientByName(String name) {
		return clientRepository.findByName(name).map(AppUtils::entityToDto);
	}
	
	@Override
	public Mono<Client> finClientByClientIdNumber(String clientNumber) {
		return null;
	}
	 */
	@Override
	public Mono<ClientDto> saveClients(Mono<ClientDto> clientDto) {
		return clientDto.map(AppUtils::dtoToEntity)
                .flatMap(clientRepository::insert)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<ClientDto> updateClients(Mono<ClientDto> client, String id) {
		return clientRepository.findById(id)
                .flatMap(p->client.map(AppUtils::dtoToEntity)
                .doOnNext(e->e.setId(id)))
                .flatMap(clientRepository::save)
                .map(AppUtils::entityToDto);
	}

	@Override
	public Mono<Void> deleteClient(String id) {
		return clientRepository.deleteById(id);
	}

	@Override
	public Flux<ClientType> findAllClientType() {
		return clientTypeRepository.findAll();
	}

	@Override
	public Mono<ClientType> findClientTypeById(String id) {
		return clientTypeRepository.findById(id);
	}

	@Override
	public Mono<ClientType> saveClientType(ClientType clientType) {
		return clientTypeRepository.save(clientType);
	}
	
	
}
