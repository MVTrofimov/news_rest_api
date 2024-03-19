package com.example.news_service_rest_api.aop;

import com.example.news_service_rest_api.exception.AccessRightsException;
import com.example.news_service_rest_api.exception.PaginationException;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.model.RoleType;
import com.example.news_service_rest_api.repository.DatabaseNewsRepository;
import com.example.news_service_rest_api.services.impl.DatabaseClientService;
import com.example.news_service_rest_api.services.impl.DatabaseCommentToNewsService;
import com.example.news_service_rest_api.services.impl.DatabaseNewsService;
import com.example.news_service_rest_api.web.models.comment.DeleteCommentRequest;
import com.example.news_service_rest_api.web.models.news.DeleteNewsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.management.relation.Role;
import java.util.Objects;
import java.util.Set;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AccessRightsAspect {

    private final DatabaseClientService clientService;

    private final DatabaseNewsService newsService;

    private final DatabaseCommentToNewsService commentToNewsService;

    @Before("@annotation(com.example.news_service_rest_api.aop.NewsUpdateAccessRightsValidation)")
    public void isUpdateOfNewsValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByClientName(userDetails.getUsername());
        News oneNews = (News) variables[0];
        News originalNews = newsService.findById(oneNews.getId());
        if (!Objects.equals(client.getId(), originalNews.getClient().getId())){
            throw new AccessRightsException("Нарушение прав доступа!");
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.NewsDeleteAccessRightsValidation)")
    public void isDeleteOfNewsValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        Long idOfDeleteNews = (Long) variables[0];
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByClientName(userDetails.getUsername());
        News originalNews = newsService.findById(idOfDeleteNews);
        Set<RoleType> roles = client.getRoles();
        if (!roles.contains(RoleType.ROLE_MODERATOR) && !roles.contains(RoleType.ROLE_ADMIN)) {
            if (!Objects.equals(client.getId(), originalNews.getClient().getId())) {
                throw new AccessRightsException("Нарушение прав доступа!");
            }
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.CommentUpdateAccessRightsValidation)")
    public void isUpdateOfCommentValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        CommentToNews comment = (CommentToNews) variables[0];
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByClientName(userDetails.getUsername());
        CommentToNews originalComment = commentToNewsService.findById(comment.getId());
        if (!Objects.equals(client.getId(), originalComment.getClient().getId())){
            throw new AccessRightsException("Нарушение прав доступа!");
        }
    }

    @Before("@annotation(com.example.news_service_rest_api.aop.CommentDeleteAccessRightsValidation)")
    public void isDeleteOfCommentValid(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        Long idOfDeleteComment = (Long) variables[0];
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByClientName(userDetails.getUsername());
        CommentToNews originalComment = commentToNewsService.findById(idOfDeleteComment);
        Set<RoleType> roles = client.getRoles();
        if (!roles.contains(RoleType.ROLE_MODERATOR) && !roles.contains(RoleType.ROLE_ADMIN)) {
            if (!Objects.equals(client.getId(), originalComment.getClient().getId())) {
                throw new AccessRightsException("Нарушение прав доступа!");
            }
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

    @Before("@annotation(com.example.news_service_rest_api.aop.ClientRoleValidation)")
    public void isEnoughRole(JoinPoint joinPoint){
        Object[] variables = joinPoint.getArgs();
        Long idOfObject = (Long) variables[0];
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails authInformation = (UserDetails) authentication.getPrincipal();
        Client client = clientService.findByClientName(authInformation.getUsername());
        Set<RoleType> roleTypes = client.getRoles();
        if (!roleTypes.contains(RoleType.ROLE_MODERATOR) && !roleTypes.contains(RoleType.ROLE_ADMIN)){
            if (!Objects.equals(idOfObject, client.getId())){
                throw new AccessRightsException("Ошибка прав доступа!");
            }
        }
    }

}
