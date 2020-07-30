package com.tekcamp.apiExercises.exceptions;

import com.tekcamp.apiExercises.model.response.CustomExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = { UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException e, WebRequest request) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(new Date(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
