package com.example.news_service_rest_api.web.models.category;

import com.example.news_service_rest_api.web.models.news.NewsListResponse;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CategoryResponse {

    private Long id;

    private String name;

}
