package com.example.news_service_rest_api.validation;

import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;


public class NewsFilterValidValidator implements ConstraintValidator<NewsFilterValid, NewsFilter> {
    @Override
    public boolean isValid(NewsFilter filter, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.anyNull(filter.getPageNumber(), filter.getPageSize());
    }
}
