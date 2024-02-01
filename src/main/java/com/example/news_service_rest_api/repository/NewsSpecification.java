package com.example.news_service_rest_api.repository;

import com.example.news_service_rest_api.model.News;
import com.example.news_service_rest_api.web.models.filters.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {


    static Specification<News> withFilter(NewsFilter filter){
        return Specification.where(byCategoryId(filter.getIdOfCategory()))
                .and(byClientId(filter.getIdOfClient()));
    }

    static Specification<News> byCategoryId(Long categoryId){
        return ((root, query, criteriaBuilder) -> {
            if (categoryId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        });
    }

    static Specification<News> byClientId(Long clientId){
        return ((root, query, criteriaBuilder) -> {
            if (clientId == null){
                return null;
            }

            return criteriaBuilder.equal(root.get("client").get("id"), clientId);
        });
    }

}
