package com.moviebazaar.movie.exception;

import com.moviebazaar.common.exception.BaseException;
import com.moviebazaar.common.exception.UserAlreadyExistsException;
import com.moviebazaar.common.pagination.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ApiResponse<?> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ApiResponse.error(ex.getMessage());
    }
    @ExceptionHandler(BaseException.class)
    public ApiResponse<?> handleBaseException(BaseException ex) {
        return ApiResponse.error(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<?> handleRuntime(RuntimeException ex) {
        return ApiResponse.error(ex.getMessage());
    }
}