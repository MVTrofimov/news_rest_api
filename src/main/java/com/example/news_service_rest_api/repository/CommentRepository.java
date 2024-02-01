package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.model.News;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<CommentToNews> findAll();

    Optional<CommentToNews> findById(Long id);

    CommentToNews save(CommentToNews comment);

    CommentToNews update(CommentToNews comment);

    void deleteById(Long id);

    void deleteByIdIn(List<Long> ids);

}
