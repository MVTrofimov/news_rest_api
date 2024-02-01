package com.example.news_service_rest_api.services;


import com.example.news_service_rest_api.model.CategoryOfNews;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;

import java.util.List;

public interface CategoryService {


    List<CategoryOfNews> findAll(Integer pageSize, Integer pageNumber);

    CategoryOfNews findById(Long id);

    CategoryOfNews save(CategoryOfNews category);

    CategoryOfNews update(CategoryOfNews category);

    void deleteById(Long id);

    void deleteByIdIn(List<Long> ids);

}
