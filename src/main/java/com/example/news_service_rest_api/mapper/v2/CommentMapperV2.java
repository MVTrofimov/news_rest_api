package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.web.models.comment.CommentListResponse;
import com.example.news_service_rest_api.web.models.comment.CommentResponse;
import com.example.news_service_rest_api.web.models.comment.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapperV2 {

    CommentResponse commentToCommentResponse(CommentToNews comment);

    CommentToNews requestToComment(UpsertCommentRequest request);

    CommentToNews requestToComment(Long id, UpsertCommentRequest request);

    default CommentListResponse commentListToCommentResponseList(List<CommentToNews> comments){
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setCommentResponseList(comments.stream().map(this::commentToCommentResponse).collect(Collectors.toList()));
        return commentListResponse;
    }

}
