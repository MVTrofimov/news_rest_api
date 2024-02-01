package com.example.news_service_rest_api.services.impl;

import com.example.news_service_rest_api.exception.EntityNotFoundException;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.repository.DatabaseClientRepository;
import com.example.news_service_rest_api.services.ClientService;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseClientService implements ClientService {

    private final DatabaseClientRepository clientRepository;


    @Override
    public List<Client> findAll(Integer pageSize, Integer pageNumber) {
        return clientRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }


    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Клиента с ID: {0} не найдено!", id)));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client currentClient = findById(client.getId());
        BeanUtils.copyProperties(client, currentClient);
        return clientRepository.save(currentClient);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
