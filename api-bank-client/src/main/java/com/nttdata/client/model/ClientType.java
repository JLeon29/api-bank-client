package com.nttdata.client.model;

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
@Document(value = "clientType")
public class ClientType {
	
	@Id
    private String id;
    @Indexed(unique = true)
    private String code;
    @Indexed(unique = true)
    private String name;
	
	
	

}
