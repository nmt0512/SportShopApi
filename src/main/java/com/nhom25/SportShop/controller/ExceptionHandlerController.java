package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.response.ResponseUtils;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity handleNullProperty(PropertyValueException e)
    {
        return ResponseUtils.error(400, "Bad Request", HttpStatus.BAD_REQUEST);
    }

}
