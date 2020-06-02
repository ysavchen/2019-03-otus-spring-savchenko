package com.mycompany.hw_l20_spring_webflux.controllers.handler;

import com.mycompany.hw_l20_spring_webflux.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    ResponseError entityNotFoundException(EntityNotFoundException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }
}
