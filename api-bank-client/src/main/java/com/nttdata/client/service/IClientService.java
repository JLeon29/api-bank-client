package com.nttdata.client.service;

import org.springframework.stereotype.Repository;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.ClientType;
import com.nttdata.client.model.dto.ClientDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {

	Mono<Client> save(Client client);

	Flux<ClientDto> listClients();

	Mono<ClientDto> findClientById(String id);

	//Mono<Client> finClientByName(String name);

	//Mono<Client> finClientByClientIdNumber(String clientNumber);

	Mono<ClientDto> saveClients(Mono<ClientDto> p);

	Mono<ClientDto> updateClients(Mono<ClientDto> clientDtoMono, String id);

	Mono<Void> deleteClient(String id);	

	Flux<ClientType> findAllClientType();
	
	Mono<ClientType> findClientTypeById(String id);
	
	Mono<ClientType> saveClientType(ClientType clientType);
	
	/*

	

	

	

	
	 */

	

}
