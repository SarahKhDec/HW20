package org.example.HW20.exceptions;

import org.example.HW20.exceptions.file.BigFileException;
import org.example.HW20.exceptions.file.FileNotFoundException;
import org.example.HW20.exceptions.file.FileNotJPGException;
import org.example.HW20.exceptions.offer.OfferNotFoundException;
import org.example.HW20.exceptions.order.LessProposedPriceException;
import org.example.HW20.exceptions.order.OrderNotFoundException;
import org.example.HW20.exceptions.time.LessTimeException;
import org.example.HW20.exceptions.user.PasswordNotMatchException;
import org.example.HW20.exceptions.user.UserInActiveException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> nullPointerException(NullPointerException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FileNotFoundException.class)
    public ResponseEntity<Object> fileNotFoundException(FileNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BigFileException.class)
    public ResponseEntity<Object> bigFileException(BigFileException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = FileNotJPGException.class)
    public ResponseEntity<Object> fileNotJPGException(FileNotJPGException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<Object> iOException(IOException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PasswordNotMatchException.class)
    public ResponseEntity<Object> passwordNotMatchException(PasswordNotMatchException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = LessTimeException.class)
    public ResponseEntity<Object> lessTimeException(LessTimeException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<Object> dateTimeParseException() {
        return new ResponseEntity<>("the format of the entered date should be as follows -> year-month-day hour:minute", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = LessProposedPriceException.class)
    public ResponseEntity<Object> lessProposedPriceException(LessProposedPriceException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    public ResponseEntity<Object> orderNotFoundException(OrderNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OfferNotFoundException.class)
    public ResponseEntity<Object> offerNotFoundException(OfferNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UserInActiveException.class)
    public ResponseEntity<Object> userInActiveException(UserInActiveException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
