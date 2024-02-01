package com.example.news_service_rest_api.services;

import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.web.models.comment.DeleteCommentRequest;

import java.util.List;

public interface CommentService {


    CommentToNews findById(Long id);

    CommentToNews save(CommentToNews comment);

    CommentToNews update(CommentToNews comment);

    void deleteById(Long id, DeleteCommentRequest request);

    void deleteByIdIn(List<Long> ids);

}
