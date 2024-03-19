package com.example.news_service_rest_api.web.controllers;

import com.example.news_service_rest_api.mapper.v2.NewsMapperV2;
import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.services.impl.DatabaseCategoryOfNewsService;
import com.example.news_service_rest_api.services.impl.DatabaseClientService;
import com.example.news_service_rest_api.services.impl.DatabaseNewsService;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import com.example.news_service_rest_api.web.models.news.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/news")
public class NewsControllerV2 {

    private final NewsMapperV2 newsMapper;

    private final DatabaseNewsService databaseNewsService;

    private final DatabaseCategoryOfNewsService categoryOfNewsService;

    private final DatabaseClientService databaseClientService;

    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(@Valid NewsFilter newsFilter){

        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(databaseNewsService.filterBy(newsFilter)));

    }

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber){
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(databaseNewsService.findAll(pageSize, pageNumber)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindByIdNewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.newsToFindByIdNewsResponse(databaseNewsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@Valid @RequestBody UpsertNewsRequest request){
        News createdNews = databaseNewsService.save(newsMapper.requestToNews(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(createdNews));
    }


    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable Long id, @Valid @RequestBody UpsertNewsRequest request){
        News updatedNews = databaseNewsService.update(newsMapper.requestToNews(id, request));

        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        databaseNewsService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
