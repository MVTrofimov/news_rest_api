package com.example.news_service_rest_api.web.models.client;

import com.example.news_service_rest_api.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequest {

    private Set<RoleType> roles;

}
