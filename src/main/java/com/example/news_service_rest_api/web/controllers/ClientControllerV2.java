package com.example.news_service_rest_api.web.controllers;


import com.example.news_service_rest_api.mapper.v2.ClientsMapperV2;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.services.impl.DatabaseClientService;
import com.example.news_service_rest_api.web.models.client.ClientListResponse;
import com.example.news_service_rest_api.web.models.client.ClientResponse;
import com.example.news_service_rest_api.web.models.client.UpsertClientRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/client")
@RequiredArgsConstructor
@Slf4j
public class ClientControllerV2 {

    private final DatabaseClientService databaseClientService;

    private final ClientsMapperV2 clientsMapper;

    @GetMapping
    public ResponseEntity<ClientListResponse> findAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber){
        log.info("Метод findAll вызван в ClientController");
        return ResponseEntity.ok(clientsMapper.clientListToClientResponseList(databaseClientService.findAll(pageSize, pageNumber)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById (@PathVariable Long id){
        log.info("Метод findById вызван в ClientController");
        return ResponseEntity.ok(clientsMapper.clientToClientResponse(databaseClientService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create (@Valid @RequestBody UpsertClientRequest request){
        Client newClient = databaseClientService.save(clientsMapper.requestToClient(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(clientsMapper.clientToClientResponse(newClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update (@PathVariable Long id, @Valid @RequestBody UpsertClientRequest request){
        Client updatedClient = databaseClientService.update(clientsMapper.requestToClient(id, request));

        return ResponseEntity.ok(clientsMapper.clientToClientResponse(updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        databaseClientService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
