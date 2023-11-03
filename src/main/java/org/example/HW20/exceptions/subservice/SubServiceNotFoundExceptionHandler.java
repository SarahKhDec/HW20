package org.example.HW20.exceptions.subservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class SubServiceNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SubServiceNotFoundException.class)
    public ResponseEntity<Object> handleSubServiceNotFoundException(SubServiceNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = ex.getMessage();
        return new ResponseEntity<>(message, status);
    }
}
