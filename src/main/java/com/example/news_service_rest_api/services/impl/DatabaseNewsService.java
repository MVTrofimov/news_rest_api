package com.example.news_service_rest_api.services.impl;

import com.example.news_service_rest_api.aop.NewsDeleteAccessRightsValidation;
import com.example.news_service_rest_api.aop.NewsUpdateAccessRightsValidation;
import com.example.news_service_rest_api.exception.EntityNotFoundException;
import com.example.news_service_rest_api.model.Client;
import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.repository.DatabaseNewsRepository;
import com.example.news_service_rest_api.repository.NewsSpecification;
import com.example.news_service_rest_api.services.NewsService;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import com.example.news_service_rest_api.web.models.news.DeleteNewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseNewsService implements NewsService {

    private final DatabaseNewsRepository newsRepository;

    private final DatabaseClientService clientService;


    @Override
    public List<News> filterBy(NewsFilter filter) {
        return newsRepository.findAll(NewsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<News> findAll(Integer pageSize, Integer pageNumber) {
        return newsRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Новости с ID: {0} не найдено", id)));
    }

    @Override
    public News save(News oneNews) {
        Client client = clientService.findById(oneNews.getClient().getId());
        oneNews.setClient(client);
        return newsRepository.save(oneNews);
    }

    @Override
    @NewsUpdateAccessRightsValidation
    public News update(News oneNews) {
        Client client = clientService.findById(oneNews.getClient().getId());
        News existedNews = findById(oneNews.getId());

        BeanUtils.copyProperties(oneNews, existedNews);
        existedNews.setClient(client);

        return newsRepository.save(existedNews);
    }

    @Override
    @NewsDeleteAccessRightsValidation
    public void deleteById(Long id, DeleteNewsRequest request) {
        newsRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        newsRepository.deleteAllById(ids);
    }
}
