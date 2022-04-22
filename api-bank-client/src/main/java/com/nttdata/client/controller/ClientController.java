package com.nttdata.client.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;
import com.nttdata.client.service.IClientService;
import com.nttdata.client.service.IClientTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	

	@Autowired
	private IClientService iClientService;

	@Autowired
	private IClientTypeService iClientTypeService;


	@GetMapping()
	public Flux<ClientDto> getClients() {
		
		return iClientService.listClients();
	}

	@GetMapping("/{id}")
	public Mono<ClientDto> getClient(@PathVariable String id) {
		
		return iClientService.findClientById(id);
	}

	@GetMapping("/name/{name}")
	public Mono<ClientDto> getClientByName(@PathVariable String name) {

		return iClientService.finClientByName(name);
	}


	@PostMapping("/create")
	public Mono<ClientDto> saveClient(@RequestBody Mono<ClientDto> clientDtoMono) {
		return iClientService.saveClients(clientDtoMono);
	}

	

	@PutMapping("/{id}")
	public Mono<ClientDto> updateClient(@RequestBody Mono<ClientDto> clientDtoMono, @PathVariable String id) {
		return iClientService.updateClients(clientDtoMono, id);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteClient(@PathVariable String id) {
		return iClientService.deleteClient(id);
	}
	
	@PostMapping()
    public Mono<ResponseEntity<Client>> createType(@RequestParam String code, @RequestBody Mono<Client> request){
        return request
        		.flatMap(clienteCreate -> iClientTypeService.findByCode(code)
        				.flatMap(type ->{
        				if(clienteCreate.getClientType() !=null && !code.equals(clienteCreate.getClientType()
        						.getCode())) {
        					return Mono.empty();
        				}else{
        					clienteCreate.setClientType(type);
                            return iClientService.save(clienteCreate);
                        }
        				
        			}))
        		.map(customerCreate -> ResponseEntity.created(URI.create("/api/client/".concat(customerCreate.getId())))
        				.contentType(MediaType.APPLICATION_JSON)
        				.body(customerCreate))
        				.switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
        		
        		
    }

	
}
