package org.example.HW20.exceptions.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiceExistExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceExistException.class)
    public ResponseEntity<Object> handleServiceExistException(ServiceExistException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        String message = ex.getMessage();
        return new ResponseEntity<>(message, status);
    }
}
