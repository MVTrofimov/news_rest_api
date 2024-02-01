package com.example.news_service_rest_api.web.models.news;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsListResponse {

    private List<NewsResponse> news = new ArrayList<>();

}
