package com.example.news_service_rest_api.web.models.client;

import com.example.news_service_rest_api.model.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {

    @NotBlank(message = "Поле имя клиента должно быть заполнено!")
    private String name;

    private String password;

    private Set<RoleType> roles;
}
