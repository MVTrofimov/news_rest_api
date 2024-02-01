package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.CategoryOfNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatabaseCategoryOfNewsRepository extends JpaRepository<CategoryOfNews, Long> {


}
