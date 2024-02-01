package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository {

    List<News> findAll();

    Optional<News> findById(Long id);

    News save(News oneNews);

    News update(News oneNews);

    void deleteById(Long id);

    void deleteByIdIn(List<Long> ids);

}
