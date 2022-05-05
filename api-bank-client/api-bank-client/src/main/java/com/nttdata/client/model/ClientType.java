package com.nttdata.client.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "clientType")
public class ClientType {
	
	@Id
    private String id;
    @Indexed(unique = true)
    @NotEmpty
    private String code;
    @Indexed(unique = true)
    @NotNull
    private String name;
    
	public ClientType(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
	
	

}
