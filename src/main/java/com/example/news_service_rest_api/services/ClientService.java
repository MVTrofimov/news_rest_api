package com.example.news_service_rest_api.services;

import com.example.news_service_rest_api.model.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll(Integer pageSize, Integer pageNumber);

    Client findById(Long id);

    Client save(Client client);

    Client update(Client client);

    void deleteById(Long id);

}
