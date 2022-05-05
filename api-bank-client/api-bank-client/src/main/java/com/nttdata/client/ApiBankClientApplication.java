package com.nttdata.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.ClientType;
import com.nttdata.client.service.IClientService;

import reactor.core.publisher.Flux;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiBankClientApplication implements CommandLineRunner{
	
	@Autowired
	private IClientService service;
	
	private static final Logger log = LoggerFactory.getLogger(ApiBankClientApplication.class);
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ApiBankClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("client").subscribe();
		mongoTemplate.dropCollection("clientType").subscribe();
		
		ClientType persona = new ClientType("P", "Persona");
		ClientType empresarial = new ClientType("E", "Empresarial");
		
		
		Flux.just(persona, empresarial)
		.flatMap(service::saveClientType)
		.doOnNext(c ->{
			log.info("ClientType create: " + c.getName() + ", Id: " + c.getId());
		}).thenMany(
				Flux.just(new Client("Juan León", "leon@hotmail.com", "93265502","JL123","Lima-Peru/Comas", empresarial),
						new Client("Armando Casas", "aemando@hotmail.com", "93631502", "AC456", "Cusco-Peru/Cusco", empresarial),
						new Client("Pedro Campos", "pedro@hotmail.com", "93265502", "PC658","Trujillo-Peru/Trujillo", persona),
						new Client("Julio Perez", "julio@hotmail.com", "93265502","JP622", "Pasco-Peru/Pasco", persona),
						new Client("Ernesto Rojas", "ernesto@hotmail.com", "93999502","ER990", "Tacna-Peru/Tacna", empresarial),
						new Client("Teodoro Paredes", "teodoro@hotmail.com", "93777502", "TP007","Tumbes-Peru/Tumbes", persona)
						)
				.flatMap(client -> {
				
					return service.save(client);
					})
		)
		.subscribe(client -> log.info("Insert: " + client.getId() + " " + client.getName()));
		
	}
	

}
