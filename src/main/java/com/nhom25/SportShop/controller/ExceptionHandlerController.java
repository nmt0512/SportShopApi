package com.nhom25.SportShop.controller;

import org.hibernate.PropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity handleNullProperty(PropertyValueException e)
    {
        return ResponseEntity.badRequest().body("Not null property error");
    }

}
