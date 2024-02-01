package com.example.news_service_rest_api.web.models.news;

import com.example.news_service_rest_api.web.models.comment.CommentListResponse;
import lombok.Data;

@Data
public class FindByIdNewsResponse {

    private Long id;

    private String nameOfNews;

    private String description;

    private Integer amountOfComments;

    private CommentListResponse comments;

}
