package com.accolite.au.exceptionHandlers;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EntityNotFoundException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomEntityNotFoundExceptionDTO.class)
    public final ResponseEntity<CustomEntityNotFoundExceptionDTO> handleAllExceptions(CustomEntityNotFoundExceptionDTO customEntityNotFoundExceptionDTO) {
        System.out.println(customEntityNotFoundExceptionDTO);
        return new ResponseEntity(customEntityNotFoundExceptionDTO, HttpStatus.NOT_FOUND);
    }
}
