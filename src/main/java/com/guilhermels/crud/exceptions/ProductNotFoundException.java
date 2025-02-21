package com.guilhermels.crud.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ProductNotFoundException extends RuntimeException{

    private final Issue issue;

    public ProductNotFoundException(String message) {
        super(message);
        this.issue = new Issue(message, HttpStatus.NOT_FOUND, new Date());
    }
}
