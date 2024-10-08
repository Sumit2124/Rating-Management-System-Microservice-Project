package com.project.user.service.exception;

import com.project.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ApiResponse>handleResourceNotFoundException(ResourceNotFoundException exception)
{
    ApiResponse apiResponse = ApiResponse.builder().message(exception.getMessage()).status(HttpStatus.NOT_FOUND).success(false).build();
    return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
}
}
