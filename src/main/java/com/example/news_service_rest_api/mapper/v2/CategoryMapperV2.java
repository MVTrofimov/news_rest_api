package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.CategoryOfNews;
import com.example.news_service_rest_api.web.models.category.CategoryListResponse;
import com.example.news_service_rest_api.web.models.category.CategoryResponse;
import com.example.news_service_rest_api.web.models.category.UpsertCategoryRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CategoryMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapperV2.class})
public interface CategoryMapperV2 {

    CategoryOfNews requestToCategory(UpsertCategoryRequest request);

    CategoryOfNews requestToCategory(Long categoryId, UpsertCategoryRequest request);

    CategoryResponse categoryToCategoryResponse(CategoryOfNews category);

    default CategoryListResponse categoryListToCategoryListResponse(List<CategoryOfNews> categoryOfNewsList){
        CategoryListResponse categoryListResponse = new CategoryListResponse();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        for (CategoryOfNews category : categoryOfNewsList){
            categoryResponses.add(categoryToCategoryResponse(category));
        }
        categoryListResponse.setCategoryResponseList(categoryResponses);
        return categoryListResponse;
    }

}
