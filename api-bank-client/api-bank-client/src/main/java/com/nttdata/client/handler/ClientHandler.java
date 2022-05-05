package com.nttdata.client.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.springframework.web.reactive.function.BodyInserters.*;

import java.net.URI;

import javax.validation.Validator;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;
import com.nttdata.client.service.IClientService;
import com.nttdata.client.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ClientHandler {
	
	
	@Autowired
	private IClientService service;
	
	@Autowired
	private Validator validator;
	
	public Mono<ServerResponse> listar(ServerRequest request){
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.listClients(),Client.class);
		
	}
	
	public Mono<ServerResponse> ver(ServerRequest request){
		
		String id = request.pathVariable("id");
		return service.findClientById(id).flatMap(c->ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(c)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> crear(ServerRequest request){
		Mono<ClientDto> client = request.bodyToMono(ClientDto.class);
		
		return service.saveClients(client).flatMap(c -> ServerResponse.created(URI.create("/api/v2/client/".concat(c.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(c)));
					

	}
	
	public Mono<ServerResponse> editar(ServerRequest request){

		Mono<Client> client = request.bodyToMono(Client.class);
		String id = request.pathVariable("id");
		
		Mono<Client> clientDb = service.findClientById(id).map(AppUtils::dtoToEntity);
		
		return clientDb.zipWith(client, (db, req) ->{
			db.setName(req.getName());
			db.setPhone(req.getPhone());
			db.setEmail(req.getEmail());
			db.setAddress(req.getAddress());
			db.setClientType(req.getClientType());
			return db;
		}).flatMap(p -> ServerResponse.created(URI.create("/api/v2/client/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(service.save(p), Client.class))
		.switchIfEmpty(ServerResponse.notFound().build());
		
	}
	
	public Mono<ServerResponse> eliminar(ServerRequest request){
		String id = request.pathVariable("id");
		
		Mono<ClientDto> clientDb = service.findClientById(id);
		
		return clientDb.flatMap(p-> service.deleteClient(id).then(ServerResponse.noContent().build()))
				.switchIfEmpty(ServerResponse.notFound().build());
		
	}
}
