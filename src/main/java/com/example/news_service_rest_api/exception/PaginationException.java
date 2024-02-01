package com.example.news_service_rest_api.exception;

public class PaginationException extends RuntimeException{

    public PaginationException(String message) {
        super(message);
    }

}
