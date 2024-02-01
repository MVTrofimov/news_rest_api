package com.example.news_service_rest_api.web.controllers;

import com.example.news_service_rest_api.exception.AccessRightsException;
import com.example.news_service_rest_api.exception.EntityNotFoundException;
import com.example.news_service_rest_api.web.models.errors.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex){
        log.error("Ошибка при попытке получить сущность!", ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = bindingResult.getAllErrors().
                stream().map(DefaultMessageSourceResolvable::getDefaultMessage).
                toList();
        String errorMessage = String.join("; ", errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessage));
    }

    @ExceptionHandler(AccessRightsException.class)
    public ResponseEntity<ErrorResponse> notValidAccess(AccessRightsException ex){
        log.error("Ошибка прав доступа!", ex);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getLocalizedMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> notValidRequestParameters(){
        ErrorResponse response = new ErrorResponse();
        response.setErrorMessage("Параметры для пагинации должны быть указаны!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
