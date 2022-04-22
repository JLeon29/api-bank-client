package com.nttdata.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.client.model.ClientType;
import com.nttdata.client.service.IClientTypeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/typeclient")
public class ClientTypeController {
	
	@Autowired
	private IClientTypeService iClientTypeService;
	
	
	@DeleteMapping
	public ResponseEntity<Mono<Void>> deleteTypeClient(@RequestBody ClientType clientType){
		
		return new ResponseEntity<Mono<Void>>(iClientTypeService.delete(clientType), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<Flux<ClientType>> listTypeClient(){
		
		return new ResponseEntity<Flux<ClientType>>(iClientTypeService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Mono<ClientType>> findByIdCustomer(@PathVariable String id){
		return new ResponseEntity<Mono<ClientType>>(iClientTypeService.findById(id),HttpStatus.OK);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<Mono<ClientType>> updateCustomer(@RequestBody ClientType clientType){
		return new ResponseEntity<Mono<ClientType>>(iClientTypeService.update(clientType),HttpStatus.OK);
	}
	
	
	@PostMapping("/save")
	public ResponseEntity<Mono<ClientType>> saveCustomer(@RequestBody ClientType clientType){
		return new ResponseEntity<Mono<ClientType>>(iClientTypeService.create(clientType), HttpStatus.CREATED);
				
	}
	
	

}