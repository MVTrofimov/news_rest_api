package com.example.news_service_rest_api.web.models.client;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpsertClientRequest {

    @NotBlank(message = "Поле имя клиента должно быть заполнено!")
    private String name;

    private String password;

}
