package com.example.news_service_rest_api.mapper.v2;


import com.example.news_service_rest_api.model.CategoryOfNews;
import com.example.news_service_rest_api.services.NewsService;
import com.example.news_service_rest_api.services.impl.DatabaseNewsService;
import com.example.news_service_rest_api.web.models.category.CategoryResponse;
import com.example.news_service_rest_api.web.models.category.UpsertCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CategoryMapperDelegate implements CategoryMapperV2{

    @Autowired
    public DatabaseNewsService databaseNewsService;

    @Autowired
    public NewsMapperV2 newsMapperV2;

    @Override
    public CategoryOfNews requestToCategory(UpsertCategoryRequest request){
        CategoryOfNews categoryOfNews = new CategoryOfNews();
        categoryOfNews.setName(request.getName());
        return categoryOfNews;
    }

    @Override
    public CategoryOfNews requestToCategory(Long categoryId, UpsertCategoryRequest request){
        CategoryOfNews currentCategory = requestToCategory(request);
        currentCategory.setId(categoryId);
        return currentCategory;
    }

    @Override
    public CategoryResponse categoryToCategoryResponse(CategoryOfNews category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setName(category.getName());
        categoryResponse.setId(category.getId());
        return categoryResponse;
    }
}
