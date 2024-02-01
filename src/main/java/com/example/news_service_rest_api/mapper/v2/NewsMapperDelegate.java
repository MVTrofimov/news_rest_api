package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.services.CategoryService;
import com.example.news_service_rest_api.services.ClientService;
import com.example.news_service_rest_api.web.models.news.UpsertNewsRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class NewsMapperDelegate implements NewsMapperV2{

    @Autowired
    private ClientService databaseClientService;

    @Autowired
    private CategoryService databaseCategoryOfNewsService;

    @Override
    public News requestToNews(UpsertNewsRequest request){
        News oneNews = new News();

        oneNews.setNameOfNews(request.getNameOfNews());
        oneNews.setDescription(request.getDescription());
        oneNews.setClient(databaseClientService.findById(request.getClientId()));
        oneNews.setCategory(databaseCategoryOfNewsService.findById(request.getCategoryId()));

        return oneNews;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request){
        News oneNews = requestToNews(request);
        oneNews.setId(newsId);

        return oneNews;
    }
}
