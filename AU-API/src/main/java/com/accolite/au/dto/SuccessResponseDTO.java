package com.accolite.au.dto;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

public class SuccessResponseDTO {
    private String message;
    private Timestamp timestamp;
    private HttpStatus status;

    public SuccessResponseDTO(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Timestamp(new Date().getTime());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
