package com.example.news_service_rest_api.web.models.comment;

import lombok.Data;

import java.util.List;

@Data
public class CommentListResponse {

    private List<CommentResponse> commentResponseList;

}
