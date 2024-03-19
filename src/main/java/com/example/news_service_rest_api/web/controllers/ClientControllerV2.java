package com.example.news_service_rest_api.web.controllers;


import com.example.news_service_rest_api.aop.ClientRoleValidation;
import com.example.news_service_rest_api.mapper.v2.ClientsMapperV2;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.services.impl.DatabaseClientService;
import com.example.news_service_rest_api.web.models.client.ClientListResponse;
import com.example.news_service_rest_api.web.models.client.ClientResponse;
import com.example.news_service_rest_api.web.models.client.UpdateClientRequest;
import com.example.news_service_rest_api.web.models.client.UpsertClientRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/client")
@RequiredArgsConstructor
@Slf4j
public class ClientControllerV2 {

    private final DatabaseClientService databaseClientService;

    private final ClientsMapperV2 clientsMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ClientListResponse> findAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber){
        log.info("Метод findAll вызван в ClientController");
        return ResponseEntity.ok(clientsMapper.clientListToClientResponseList(databaseClientService.findAll(pageSize, pageNumber)));
    }

    @GetMapping("/{id}")
    @ClientRoleValidation
    public ResponseEntity<ClientResponse> findById (@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails){
        log.info("Метод findById вызван в ClientController");
        return ResponseEntity.ok(clientsMapper.clientToClientResponse(databaseClientService.findById(id)));
    }

    @PutMapping("/{id}")
    @ClientRoleValidation
    public ResponseEntity<ClientResponse> update (@PathVariable Long id, @Valid @RequestBody UpdateClientRequest request){
        Client updatedClient = databaseClientService.update(clientsMapper.requestToClient(id, request));

        return ResponseEntity.ok(clientsMapper.clientToClientResponse(updatedClient));
    }

    @DeleteMapping("/{id}")
    @ClientRoleValidation
    public ResponseEntity<Void> delete (@PathVariable Long id){
        databaseClientService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
