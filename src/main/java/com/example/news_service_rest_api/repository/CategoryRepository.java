package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.CategoryOfNews;
import com.example.news_service_rest_api.model.Client;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<CategoryOfNews> findAll();

    Optional<CategoryOfNews> findById(Long id);

    CategoryOfNews save(CategoryOfNews category);

    CategoryOfNews update(CategoryOfNews category);

    void deleteById(Long id);

    void deleteByIdIn(List<Long> ids);

}
