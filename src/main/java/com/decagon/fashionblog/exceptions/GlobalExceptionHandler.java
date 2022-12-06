package com.decagon.fashionblog.exceptions;

import com.decagon.fashionblog.pojos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleNotFoundExceptions(NotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApiResponse<String> handleIncorrectPasswordException(IncorrectPasswordException ex){
        return new ApiResponse<>(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), null);
    }

    @ExceptionHandler(Notification.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse<String> handleNotification(Notification ex){
        return new ApiResponse<>(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), null);
    }
}
