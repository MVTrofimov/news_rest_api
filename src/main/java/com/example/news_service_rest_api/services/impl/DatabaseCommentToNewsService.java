package com.example.news_service_rest_api.services.impl;

import com.example.news_service_rest_api.aop.CommentDeleteAccessRightsValidation;
import com.example.news_service_rest_api.aop.CommentUpdateAccessRightsValidation;
import com.example.news_service_rest_api.exception.EntityNotFoundException;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.model.CommentToNews;
import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.repository.DatabaseCommentToNewsRepository;
import com.example.news_service_rest_api.services.CommentService;
import com.example.news_service_rest_api.web.models.comment.DeleteCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseCommentToNewsService implements CommentService {

    private final DatabaseCommentToNewsRepository commentToNewsRepository;

    private final DatabaseClientService clientService;

    private final DatabaseNewsService newsService;



    @Override
    public CommentToNews findById(Long id) {
        return commentToNewsRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(MessageFormat.format("Комментарий с ID: {0} не найден", id)));
    }

    @Override
    public CommentToNews save(CommentToNews comment) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByClientName(userDetails.getUsername());
        News oneNews = newsService.findById(comment.getOneNews().getId());

        oneNews.setAmountOfComments(oneNews.getAmountOfComments() + 1);
        comment.setOneNews(oneNews);
        comment.setClient(client);

        return commentToNewsRepository.save(comment);
    }

    @Override
    @CommentUpdateAccessRightsValidation
    public CommentToNews update(CommentToNews comment) {
        CommentToNews existedComment = findById(comment.getId());
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = clientService.findByClientName(userDetails.getUsername());
        News oneNews = newsService.findById(comment.getOneNews().getId());

        BeanUtils.copyProperties(comment, existedComment);

        existedComment.setClient(client);
        existedComment.setOneNews(oneNews);

        commentToNewsRepository.save(existedComment);
        return existedComment;
    }

    @Override
    @CommentDeleteAccessRightsValidation
    public void deleteById(Long id) {
        CommentToNews comment = findById(id);
        News oneNews = comment.getOneNews();
        oneNews.setAmountOfComments(oneNews.getAmountOfComments() - 1);
        commentToNewsRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        commentToNewsRepository.deleteAllById(ids);
    }
}
