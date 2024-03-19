package com.example.news_service_rest_api.services.impl;

import com.example.news_service_rest_api.aop.ClientRoleValidation;
import com.example.news_service_rest_api.exception.EntityNotFoundException;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.repository.DatabaseClientRepository;
import com.example.news_service_rest_api.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DatabaseClientService implements ClientService {

    private final DatabaseClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

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
        currentClient.setRoles(client.getRoles());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updatedAuthorities = (currentClient.getRoles().stream().map(roleType -> new SimpleGrantedAuthority(roleType.toString())).collect(Collectors.toList()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);

        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return clientRepository.saveAndFlush(currentClient);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }


    public Client createNewAccount(Client client){
        client.setRoles(client.getRoles());
        client.setPassword(passwordEncoder.encode(client.getPassword()));


        return clientRepository.saveAndFlush(client);
    }

    public Boolean existsByName(String name){
        return clientRepository.existsByName(name);
    }

    public Client findByClientName(String name){
        return clientRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Username not found!"));
    }
}
