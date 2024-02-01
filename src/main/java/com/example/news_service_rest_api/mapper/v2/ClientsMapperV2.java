package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.services.ClientService;
import com.example.news_service_rest_api.web.models.client.ClientListResponse;
import com.example.news_service_rest_api.web.models.client.ClientResponse;
import com.example.news_service_rest_api.web.models.client.UpsertClientRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(ClientsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapperV2.class})
public interface ClientsMapperV2 {

    Client requestToClient(UpsertClientRequest request);

    Client requestToClient(Long clientId, UpsertClientRequest request);

    ClientResponse clientToClientResponse(Client client);

    default ClientListResponse clientListToClientResponseList(List<Client> clients){
        ClientListResponse clientListResponse = new ClientListResponse();
        clientListResponse.setClients(clients.stream().map(this::clientToClientResponse).collect(Collectors.toList()));
        return clientListResponse;
    }

}
