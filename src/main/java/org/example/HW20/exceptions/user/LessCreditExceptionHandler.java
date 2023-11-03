package org.example.HW20.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LessCreditExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LessCreditException.class)
    public ResponseEntity<Object> handleLessCreditException(LessCreditException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();
        return new ResponseEntity<>(message, status);
    }
}
