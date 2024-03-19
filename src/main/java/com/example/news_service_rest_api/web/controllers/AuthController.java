package com.example.news_service_rest_api.web.controllers;

import com.example.news_service_rest_api.exception.AlreadyExistsException;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.services.impl.DatabaseClientService;
import com.example.news_service_rest_api.web.models.client.CreateClientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthController {

    private final DatabaseClientService clientService;

    @PostMapping("/account")
    public ResponseEntity<CreateClientRequest> createClientAccount(@RequestBody CreateClientRequest clientRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createAccount(clientRequest));
    }


    private CreateClientRequest createAccount(CreateClientRequest clientRequest){
        if (clientService.existsByName(clientRequest.getName())){
            throw new AlreadyExistsException(MessageFormat.format("Пользователь с именем: {0} уже существует!", clientRequest.getName()));
        }
        var client = new Client();
        client.setPassword(clientRequest.getPassword());
        client.setName(clientRequest.getName());
        client.setRoles(clientRequest.getRoles());

        var createdClient = clientService.createNewAccount(client);

        return new CreateClientRequest(createdClient.getName(), createdClient.getPassword(), clientRequest.getRoles());
    }

}
