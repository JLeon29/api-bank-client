package com.nttdata.client.utils;

import org.springframework.beans.BeanUtils;

import com.nttdata.client.model.Client;
import com.nttdata.client.model.dto.ClientDto;

public class AppUtils {
	
	public static ClientDto entityToDto(Client client){
        ClientDto clientDto=new ClientDto();
        BeanUtils.copyProperties(client,clientDto);
        return clientDto;
    }

    public static Client dtoToEntity(ClientDto clientDto){
        Client client=new Client();
        BeanUtils.copyProperties(clientDto,client);
        return client;
    }

}
