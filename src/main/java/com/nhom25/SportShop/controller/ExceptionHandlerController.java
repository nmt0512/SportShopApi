package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@CrossOrigin(maxAge = 7200)
@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<ResponseData<Void>> handleNullProperty(PropertyValueException e) {
        return ResponseUtils.error(400, "Bad Request", HttpStatus.BAD_REQUEST);
    }
}
