package com.example.news_service_rest_api.mapper.v2;

import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.web.models.news.FindByIdNewsResponse;
import com.example.news_service_rest_api.web.models.news.NewsListResponse;
import com.example.news_service_rest_api.web.models.news.NewsResponse;
import com.example.news_service_rest_api.web.models.news.UpsertNewsRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapperV2.class})
public interface NewsMapperV2 {

   News requestToNews(UpsertNewsRequest request);

   News requestToNews(Long newsId, UpsertNewsRequest request);

   NewsResponse newsToResponse(News oneNews);

   FindByIdNewsResponse newsToFindByIdNewsResponse(News oneNews);

   default NewsListResponse newsListToNewsListResponse(List<News> news){
       NewsListResponse newsListResponse = new NewsListResponse();
       newsListResponse.setNews(news.stream().map(this::newsToResponse).collect(Collectors.toList()));
       return newsListResponse;
   }
}
