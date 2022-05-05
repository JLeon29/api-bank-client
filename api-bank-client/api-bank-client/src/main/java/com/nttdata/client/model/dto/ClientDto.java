package com.nttdata.client.model.dto;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.nttdata.client.model.ClientType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
	
	@Id
	private String id;
	private String name;
	@Email
	private String email;
	private String phone;
	private String address;

	private ClientType clientType;

}
