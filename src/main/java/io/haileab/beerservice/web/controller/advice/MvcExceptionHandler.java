package io.haileab.beerservice.web.controller.advice;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> validationExceptionHandler(ConstraintViolationException e){
        List<String> exceptions = e.getConstraintViolations()
                .stream()
                .map(
                        violation -> violation.getPropertyPath() + " : " + violation.getMessage()
                )
                .toList();
        return new ResponseEntity<>(exceptions, HttpStatus.BAD_REQUEST);
    }
}
