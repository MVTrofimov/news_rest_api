package com.example.news_service_rest_api.web.models.category;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryListResponse {

    private List<CategoryResponse> categoryResponseList = new ArrayList<>();

}
