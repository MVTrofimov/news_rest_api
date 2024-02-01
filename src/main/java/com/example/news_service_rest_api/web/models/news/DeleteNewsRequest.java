package com.example.news_service_rest_api.web.models.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteNewsRequest {

    @Positive(message = "ID клиента может быть только положительным!")
    private Long clientId;

}
