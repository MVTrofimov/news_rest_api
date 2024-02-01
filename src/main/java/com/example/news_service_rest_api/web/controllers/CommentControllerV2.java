package com.example.news_service_rest_api.web.controllers;

import com.example.news_service_rest_api.mapper.v2.CommentMapperV2;
import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.services.impl.DatabaseCommentToNewsService;
import com.example.news_service_rest_api.web.models.comment.CommentResponse;
import com.example.news_service_rest_api.web.models.comment.DeleteCommentRequest;
import com.example.news_service_rest_api.web.models.comment.UpsertCommentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/comment")
@RequiredArgsConstructor
public class CommentControllerV2 {

    private final CommentMapperV2 commentMapper;

    private final DatabaseCommentToNewsService databaseCommentService;


    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentMapper.commentToCommentResponse(databaseCommentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> save(@Valid @RequestBody UpsertCommentRequest request){
        CommentToNews comment = commentMapper.requestToComment(request);
        databaseCommentService.save(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToCommentResponse(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> update(@PathVariable Long id, @Valid @RequestBody UpsertCommentRequest request){
        CommentToNews comment = commentMapper.requestToComment(id, request);
        databaseCommentService.update(comment);
        return ResponseEntity.ok(commentMapper.commentToCommentResponse(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, @Valid @RequestBody DeleteCommentRequest request){
        databaseCommentService.deleteById(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
