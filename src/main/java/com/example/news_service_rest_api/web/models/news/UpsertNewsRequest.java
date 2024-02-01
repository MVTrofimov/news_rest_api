package com.example.news_service_rest_api.web.models.news;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpsertNewsRequest {


    @Positive(message = "ID клиента может быть только положительным!")
    private Long clientId;

    @NotBlank(message = "Поле название новости должно быть заполнено!")
    private String nameOfNews;

    @NotBlank(message = "Поле описание новости должно быть заполнено!")
    private String description;

    @Positive(message = "ID категории может быть только положительным!")
    private Long categoryId;

}
