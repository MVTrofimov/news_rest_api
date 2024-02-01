package com.example.news_service_rest_api.services;

import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import com.example.news_service_rest_api.web.models.news.DeleteNewsRequest;

import java.util.List;

public interface NewsService {

    List<News> filterBy(NewsFilter filter);

    List<News> findAll(Integer pageSize, Integer pageNumber);

    News findById(Long id);

    News save(News oneNews);

    News update(News oneNews);

    void deleteById(Long id, DeleteNewsRequest request);

    void deleteByIdIn(List<Long> ids);

}
