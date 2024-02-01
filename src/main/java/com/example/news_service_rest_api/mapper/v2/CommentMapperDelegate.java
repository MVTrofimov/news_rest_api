package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.services.ClientService;
import com.example.news_service_rest_api.services.NewsService;
import com.example.news_service_rest_api.web.models.comment.CommentResponse;
import com.example.news_service_rest_api.web.models.comment.UpsertCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;



public abstract class CommentMapperDelegate implements CommentMapperV2{

    @Autowired
    private ClientService databaseClientService;

    @Autowired
    private NewsService databaseNewsService;


    @Override
    public CommentToNews requestToComment(UpsertCommentRequest request){
        CommentToNews comment = new CommentToNews();

        comment.setClient(databaseClientService.findById(request.getClientId()));
        comment.setOneNews(databaseNewsService.findById(request.getOneNewsId()));
        comment.setComment(request.getComment());

        return comment;
    }

    @Override
    public CommentToNews requestToComment(Long id, UpsertCommentRequest request){
        CommentToNews comment = requestToComment(request);
        comment.setId(id);
        return comment;
    }





}
