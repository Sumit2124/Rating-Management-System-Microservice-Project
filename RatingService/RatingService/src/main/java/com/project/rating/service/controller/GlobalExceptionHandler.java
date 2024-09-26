package com.project.rating.service.controller;

import com.project.rating.service.exceptions.RatingNotFoundException;
import com.project.rating.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiResponse> handleRatingNotFoundException(RatingNotFoundException r)
    {
        ApiResponse apiResponse = ApiResponse.builder().msg(r.getMessage()).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
}
