package com.moviebazaar.common.exception;

import com.moviebazaar.common.pagination.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntime(RuntimeException ex) {
        return ApiResponse.error(ex.getMessage());
    }
}