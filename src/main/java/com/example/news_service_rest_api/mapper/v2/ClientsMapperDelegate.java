package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.web.models.client.UpdateClientRequest;
import com.example.news_service_rest_api.web.models.client.UpsertClientRequest;

public abstract class ClientsMapperDelegate implements ClientsMapperV2{

    @Override
    public Client requestToClient(Long clientId, UpdateClientRequest request){
        Client client = new Client();
        client.setId(clientId);
        client.setRoles(request.getRoles());
        return client;
    }

}
