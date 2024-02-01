package com.example.news_service_rest_api.web.models.category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpsertCategoryRequest {

    @NotBlank(message = "Поле название категории должно быть заполнено!")
    private String name;

}
