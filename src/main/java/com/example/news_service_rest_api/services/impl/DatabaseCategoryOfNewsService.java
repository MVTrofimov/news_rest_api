package com.example.news_service_rest_api.services.impl;

import com.example.news_service_rest_api.exception.EntityNotFoundException;
import com.example.news_service_rest_api.model.CategoryOfNews;
import com.example.news_service_rest_api.repository.DatabaseCategoryOfNewsRepository;
import com.example.news_service_rest_api.services.CategoryService;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseCategoryOfNewsService implements CategoryService {

    private final DatabaseCategoryOfNewsRepository categoryOfNewsRepository;


    @Override
    public List<CategoryOfNews> findAll(Integer pageSize, Integer pageNumber) {
        return categoryOfNewsRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent();
    }

    @Override
    public CategoryOfNews findById(Long id) {
        return categoryOfNewsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("Категория с ID: {0} не найдена!", id)));

    }

    @Override
    public CategoryOfNews save(CategoryOfNews category) {
        return categoryOfNewsRepository.save(category);
    }

    @Override
    public CategoryOfNews update(CategoryOfNews category) {
        CategoryOfNews existedCategory = findById(category.getId());
        BeanUtils.copyProperties(category, existedCategory);
        return categoryOfNewsRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryOfNewsRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        categoryOfNewsRepository.deleteAllById(ids);
    }
}
