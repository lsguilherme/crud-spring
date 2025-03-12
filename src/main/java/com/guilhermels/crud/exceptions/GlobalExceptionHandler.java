package com.guilhermels.crud.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public Issue handleProductNotFoundException(ProductNotFoundException e){
        String requestId = MDC.get("requestId");
        log.error("[{}] Exception ocorred: {}", requestId, e.getMessage());
        return new Issue(e.getMessage(), HttpStatus.NOT_FOUND, new Date());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Issue handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new Issue(errors.toString(), HttpStatus.BAD_REQUEST, new Date());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Issue handleException(Exception e){
        String requestId = MDC.get("requestId");
        log.error("[{}] Exception ocorred: {}", requestId, e.getMessage());
        return new Issue(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, new Date());
    }
}
