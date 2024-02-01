package com.example.news_service_rest_api.web.models.news;

import lombok.Data;

@Data
public class NewsResponse {

    private Long id;

    private String nameOfNews;

    private String description;

    private Integer amountOfComments;

}
