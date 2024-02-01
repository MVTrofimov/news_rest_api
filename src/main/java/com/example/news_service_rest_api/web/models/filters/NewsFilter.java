package com.example.news_service_rest_api.web.models.filters;

import com.example.news_service_rest_api.validation.NewsFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NewsFilterValid
public class NewsFilter {

    private Integer pageSize;
    private Integer pageNumber;
    private Long idOfCategory;

    private Long idOfClient;
}
