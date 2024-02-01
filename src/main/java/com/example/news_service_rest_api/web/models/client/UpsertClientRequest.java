package com.example.news_service_rest_api.web.models.client;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertClientRequest {

    @NotBlank(message = "Поле имя клиента должно быть заполнено!")
    private String name;

}
