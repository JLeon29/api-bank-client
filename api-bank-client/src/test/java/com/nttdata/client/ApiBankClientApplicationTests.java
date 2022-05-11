package com.nttdata.client;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.nttdata.client.model.dto.ClientDto;

import ch.qos.logback.core.net.server.Client;

@RunWith(SpringRunner.class) //
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//
class ApiBankClientApplicationTests {
	
	@Autowired // importamos WebTestClient
	private WebTestClient client;

	@Test
	void listarTest() {
		
		client.get() // utilizamos get porque es una lista a la cual le haremos una prueba
		.uri("/api/v2/client") // ruta
		.accept(MediaType.APPLICATION_JSON_UTF8)  // queremos consumir un JSON en el response
		.exchange() // PARA ENVIAR NUESTOR REQUEST AL ENDPOINT y consumir nuestro apirest
		.expectStatus().isOk() // el estatus que esperamos
		.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8) // queremos comprobar las cabeceras
		.expectBodyList(ClientDto.class) // contenido de la respuesta, en este caso retorna una lista de clientes
		.hasSize(6); // CUANTOS ELEMENTOS TIENE LA LISTA
	}

}
