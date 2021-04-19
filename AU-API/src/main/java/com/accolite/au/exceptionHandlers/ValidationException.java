package com.accolite.au.exceptionHandlers;

import com.accolite.au.dto.ExceptionDetailsDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> exceptionMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            System.out.println(error);
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            exceptionMap.put(fieldName, message);
        });
        ExceptionDetailsDTO exceptionDetailsDTO = new ExceptionDetailsDTO(exceptionMap, new Timestamp(new Date().getTime()), HttpStatus.BAD_REQUEST);
        return new ResponseEntity(exceptionDetailsDTO, HttpStatus.BAD_REQUEST);
    }
}