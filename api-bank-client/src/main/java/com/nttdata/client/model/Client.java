package com.nttdata.client.model;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nttdata.client.model.dto.ClientDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Flux;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "client")
public class Client implements Serializable {
	
	
	private static final long serialVersionUID = 3525927559187820923L;
	
	@Id
	private String id;
	private String name;
	private String clientIdType;
	@Indexed(unique = true)
	private String clientIdNumber;
	@Email
	private String email;
	private String phone;
	private String address;

	private ClientType clientType;

	
	

}
