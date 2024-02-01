package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface DatabaseNewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {


}
