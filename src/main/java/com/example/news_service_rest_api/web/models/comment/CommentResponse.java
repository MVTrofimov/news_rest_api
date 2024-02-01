package com.example.news_service_rest_api.web.models.comment;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentResponse {

    private Long id;

    private String comment;

    private Instant updateAt;

}
