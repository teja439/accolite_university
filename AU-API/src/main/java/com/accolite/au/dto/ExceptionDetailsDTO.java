package com.accolite.au.dto;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Map;

public class ExceptionDetailsDTO {
    private Map<String, String> errors;
    private Timestamp timestamp;
    private HttpStatus status;

    public ExceptionDetailsDTO(Map<String, String> errors, Timestamp timestamp, HttpStatus status) {
        this.errors = errors;
        this.timestamp = timestamp;
        this.status = status;
    }
}
