package com.accolite.au.dto;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

public class CustomEntityNotFoundExceptionDTO extends RuntimeException{
    private String messsage;
    private Timestamp timestamp;
    private HttpStatus status = HttpStatus.NOT_FOUND;

    public CustomEntityNotFoundExceptionDTO(String message){
        this.messsage = message;
        this.timestamp = new Timestamp(new Date().getTime());
    }
}