package com.example.news_service_rest_api.web.models.comment;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpsertCommentRequest {

    @Positive(message = "ID новости может быть только положительным!")
    private Long oneNewsId;

    @NotBlank(message = "Поле комментария к новости должно быть заполнено!")
    private String comment;

}
