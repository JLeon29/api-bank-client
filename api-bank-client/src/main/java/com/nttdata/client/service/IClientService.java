package com.nttdata.client.service;

import org.springframework.stereotype.Repository;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {

	Mono<Client> save(Client client);

	Flux<ClientDto> listClients();

	Mono<ClientDto> findClientById(String id);

	Mono<ClientDto> finClientByName(String name);

	Mono<ClientDto> finClientByClientIdNumber(String clientNumber);

	Mono<ClientDto> saveClients(Mono<ClientDto> clientDtoMono);

	Mono<ClientDto> updateClients(Mono<ClientDto> clientDtoMono, String id);

	Mono<Void> deleteClient(String id);

	

}
