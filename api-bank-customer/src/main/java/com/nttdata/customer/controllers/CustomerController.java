package com.nttdata.customer.controllers;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.nttdata.customer.impl.CustomerServiceImpl;
import com.nttdata.customer.impl.CustomerTypeServiceImpl;
import com.nttdata.customer.models.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CustomerController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
    private CustomerTypeServiceImpl typeService;

    @Autowired
    private CustomerServiceImpl service;
    
    @GetMapping("/customer")
    public ResponseEntity<Flux<Customer>> findAll(){
        return new ResponseEntity<Flux<Customer>>(service.findAll(), HttpStatus.OK);
    }
    
    @PostMapping("/customer")
    public Mono<ResponseEntity<Customer>> create(@RequestParam String code,@RequestBody Mono<Customer> request){
        return request
                .flatMap(customerCreate -> typeService.findByCode(code)
                        .flatMap(type ->{
                            if(customerCreate.getCustomerType() !=null && !code.equals(customerCreate.getCustomerType().getCode())) {
                                return Mono.empty();
                            } else{
                                customerCreate.setCustomerType(type);
                                return service.save(customerCreate);
                            }
                        }))
                .map(customerCreate -> ResponseEntity.created(URI.create("/api/customers/".concat(customerCreate.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(customerCreate))
                .switchIfEmpty(Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
    }
	
    @PutMapping("/customer/{id}")
    public Mono<ResponseEntity<Customer>> update(@PathVariable String id, @RequestBody Customer customer){
        return service.findById(id).flatMap( c ->{
            c.setName(customer.getName());
            c.setCustomerIdentityNumber(customer.getCustomerIdentityNumber());
            c.setCustomerIdentityType(customer.getCustomerIdentityType());
            return service.save(c);
        }).map(c-> ResponseEntity.created(URI.create("/api/customers/".concat(c.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(c)).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/customer/{id}")
    public Mono<ResponseEntity<Customer>> delete(@PathVariable String id){
        return service.findById(id).flatMap(customer -> service.delete(customer))
                .map(c -> ResponseEntity
                        .noContent().build());

    }


    @GetMapping("/customer/findCustomerCredit/{customerIdentityNumber}")
    public Mono<ResponseEntity<Customer>> findCustomerCredit(@PathVariable String customerIdentityNumber){
        LOGGER.info("findByCustomerIdentityNumber: customerIdentityNumber={}", customerIdentityNumber);
        return service.findByCustomerIdentityNumber(customerIdentityNumber)
                .map(saveCustomer -> ResponseEntity.ok(saveCustomer))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    
    @PutMapping(value = "/cards/{id}")
    public Mono<ResponseEntity<Object>> updateCards(@PathVariable(value = "id") String id,
                                                 @RequestBody Customer customer) {
        LOGGER.info("update: {}", customer);
        return service.updateCard(id, customer)
                .flatMap(customerUpdate -> Mono.just(ResponseEntity.ok(customerUpdate))
                        );
    }

}
