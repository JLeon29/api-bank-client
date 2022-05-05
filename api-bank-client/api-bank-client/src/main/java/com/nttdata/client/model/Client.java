package com.nttdata.client.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@Document(collection ="client")
public class Client implements Serializable {
	
	
	private static final long serialVersionUID = 3525927559187820923L;
	
	@Id
	@NotEmpty
	private String id;
	@NotNull
	private String name;
	@Email
	private String email;
	private String phone;
	private String clientIdNumber;
	private String address;
	@Valid
	@NotNull
	private ClientType clientType;
	
	public Client(String name, @Email String email, String phone, String clientIdNumber, String address, @Valid ClientType clientType) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.clientIdNumber= clientIdNumber;
		this.address = address;
		this.clientType = clientType;
	}

	
	

}
