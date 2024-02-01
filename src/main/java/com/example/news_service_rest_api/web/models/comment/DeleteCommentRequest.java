package com.example.news_service_rest_api.web.models.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DeleteCommentRequest {


    @Positive(message = "ID клиента может быть только положительным!")
    private Long clientId;

}
