package com.guilhermels.crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Issue handleProductNotFoundException(ProductNotFoundException e){
        return new Issue(e.getMessage(), HttpStatus.NOT_FOUND, new Date());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Issue handleException(Exception e){
        return new Issue(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date());
    }
}
