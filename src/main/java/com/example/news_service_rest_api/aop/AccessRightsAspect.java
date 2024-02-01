package com.example.news_service_rest_api.aop;

import com.example.news_service_rest_api.exception.AccessRightsException;
import com.example.news_service_rest_api.exception.PaginationException;
import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.repository.DatabaseNewsRepository;
import com.example.news_service_rest_api.services.impl.DatabaseCommentToNewsService;
import com.example.news_service_rest_api.services.impl.DatabaseNewsService;
import com.example.news_service_rest_api.web.models.comment.DeleteCommentRequest;
import com.example.news_service_rest_api.web.models.news.DeleteNewsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AccessRightsAspect {

    private final DatabaseNewsService newsService;

    private final DatabaseCommentToNewsService commentToNewsService;

    @Before("@annotation(com.example.news_service_rest_api.aop.NewsUpdateAccessRightsValidation)")
    public void isUpdateOfNewsValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        News oneNews = (News) variables[0];
        News originalNews = newsService.findById(oneNews.getId());
        if (!Objects.equals(oneNews.getClient().getId(), originalNews.getClient().getId())){
            throw new AccessRightsException("Нарушение прав доступа!");
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.NewsDeleteAccessRightsValidation)")
    public void isDeleteOfNewsValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        Long idOfDeleteNews = (Long) variables[0];
        DeleteNewsRequest request = (DeleteNewsRequest) variables[1];
        News originalNews = newsService.findById(idOfDeleteNews);
        if (!Objects.equals(request.getClientId(), originalNews.getClient().getId())){
            throw new AccessRightsException("Нарушение прав доступа!");
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.CommentUpdateAccessRightsValidation)")
    public void isUpdateOfCommentValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        CommentToNews comment = (CommentToNews) variables[0];
        CommentToNews originalComment = commentToNewsService.findById(comment.getId());
        if (!Objects.equals(comment.getClient().getId(), originalComment.getClient().getId())){
            throw new AccessRightsException("Нарушение прав доступа!");
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.CommentDeleteAccessRightsValidation)")
    public void isDeleteOfCommentValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        Long idOfDeleteComment = (Long) variables[0];
        DeleteCommentRequest request = (DeleteCommentRequest) variables[1];
        CommentToNews originalComment = commentToNewsService.findById(idOfDeleteComment);
        if (!Objects.equals(request.getClientId(), originalComment.getClient().getId())){
            throw new AccessRightsException("Нарушение прав доступа!");
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.PaginationValidation)")
    public void isPaginationParametersValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        Integer pageSize = (Integer) variables[0];
        Integer pageNumber = (Integer) variables[1];
        if (pageSize.equals(null) || pageNumber.equals(null)){
            throw new PaginationException("Параметры пагинации должны быть указаны!");
        }
    }

}
