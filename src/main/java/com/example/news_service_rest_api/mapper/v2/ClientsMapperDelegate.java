package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.web.models.client.UpsertClientRequest;

public abstract class ClientsMapperDelegate implements ClientsMapperV2{

    @Override
    public Client requestToClient(UpsertClientRequest request){
        Client client = new Client();
        client.setName(request.getName());

        return client;
    }

    @Override
    public Client requestToClient(Long clientId, UpsertClientRequest request){
        Client client = requestToClient(request);
        client.setId(clientId);

        return client;
    }

}
