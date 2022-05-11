	package com.nttdata.client.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.support.WebExchangeBindException;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;
import com.nttdata.client.service.IClientService;
import com.nttdata.client.utils.AppUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	

	@Autowired
	private IClientService iClientService;
	/*
	@GetMapping()
	public Flux<ClientDto> getClients() {
		
		return iClientService.listClients();
	}
	*/
	
	@GetMapping
	public Mono<ResponseEntity<Flux<ClientDto>>> getClients(){
		return Mono.just(
				ResponseEntity.ok() // el estado - 200 ok
				.contentType(MediaType.APPLICATION_JSON_UTF8) // tipo de archivo que guardaremos
				.body(iClientService.listClients()) // guardamos el contenido
				);
	}
	
	/*
	@GetMapping("/{id}")
	public Mono<ClientDto> getClient(@PathVariable String id) {
		
		return iClientService.findClientById(id);
	}
	*/
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<ClientDto>> getClient(@PathVariable String id){
		return iClientService.findClientById(id).map(p -> ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	/*
	@GetMapping("/name/{name}")
	public Mono<ClientDto> getClientByName(@PathVariable String name) {

		return iClientService.finClientByName(name);
	}
	*/

	@PostMapping
	public Mono<ResponseEntity<Map<String, Object>>> crear(@Valid @RequestBody Mono<ClientDto> clientDto){
		
		Map<String, Object> respuesta = new HashMap<String, Object>();
		
		return iClientService.saveClients(clientDto).map(p-> {
			respuesta.put("client", p);
			respuesta.put("mensaje", "Cliente creado con exito");
			
			return ResponseEntity
				.created(URI.create("/api/client/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(respuesta);
			
			
			}).onErrorResume(t -> {
				return Mono.just(t).cast(WebExchangeBindException.class)
						.flatMap(e -> Mono.just(e.getFieldErrors()))
						.flatMapMany(Flux::fromIterable)
						.map(fieldError -> "El campo "+fieldError.getField() + " " + fieldError.getDefaultMessage())
						.collectList()
						.flatMap(list -> {
							respuesta.put("errors", list);
							respuesta.put("status", HttpStatus.BAD_REQUEST.value());
							return Mono.just(ResponseEntity.badRequest().body(respuesta));
						});
			
			
			});
	
		}

	
	

	@PutMapping("/{id}")
	public Mono<ClientDto> updateClient(@RequestBody Mono<ClientDto> clientDtoMono, @PathVariable String id) {
		return iClientService.updateClients(clientDtoMono, id);
	}

	

	@DeleteMapping("/{id}")
	public Mono<Void> deleteClient(@PathVariable String id) {
		return iClientService.deleteClient(id);
	}
	/*
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
    */

	
}
