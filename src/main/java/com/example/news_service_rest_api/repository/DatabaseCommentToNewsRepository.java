package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.CommentToNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseCommentToNewsRepository extends JpaRepository<CommentToNews, Long> {
}
