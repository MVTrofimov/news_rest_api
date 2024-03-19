package com.example.news_service_rest_api.security;

import com.example.news_service_rest_api.model.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class AppClientPrincipal implements UserDetails {

    private final Client client;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return client.getRoles().stream().map(roleType -> new SimpleGrantedAuthority(roleType.toString())).toList();
    }

    @Override
    public String getPassword() {
        return client.getPassword();
    }

    @Override
    public String getUsername() {
        return client.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
