package com.nttdata.client.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;

import reactor.core.publisher.Mono;

@Configuration
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
	
	//Mono<ClientDto> findByName(String name);
    //Mono<ClientDto> findByClientIdNumber(String clientIdNumber);

}
