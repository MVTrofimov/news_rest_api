package com.example.news_service_rest_api.security;

import com.example.news_service_rest_api.services.ClientService;
import com.example.news_service_rest_api.services.impl.DatabaseClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements UserDetailsService {

    private final DatabaseClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppClientPrincipal(clientService.findByClientName(username));
    }
}
