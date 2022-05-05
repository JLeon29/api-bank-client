package com.nttdata.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.client.handler.ClientHandler;
import com.nttdata.client.model.Client;
import com.nttdata.client.service.IClientService;

import jdk.jfr.ContentType;

@Configuration
public class RouterConfig {

	@Bean
	public RouterFunction<ServerResponse> routes(ClientHandler handler){
		
		return RouterFunctions.route(RequestPredicates.GET("api/v2/client").or(RequestPredicates.GET("api/v3/client")), handler::listar)
			
		.andRoute(RequestPredicates.GET("/api/v2/client/{id}"), handler::ver)
		.andRoute(RequestPredicates.POST("/api/v2/client"), handler::crear)
		.andRoute(RequestPredicates.PUT("/api/v2/client/{id}"), handler::editar)
		.andRoute(RequestPredicates.DELETE("/api/v2/client/{id}"), handler::eliminar);
	}

}
