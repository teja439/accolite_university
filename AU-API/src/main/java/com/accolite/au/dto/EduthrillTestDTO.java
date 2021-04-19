package com.accolite.au.dto;

import com.accolite.au.models.EduthrillSession;
import javax.validation.constraints.NotNull;

import com.accolite.au.models.EduthrillTest;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

public class EduthrillTestDTO {
    private int eduthrillTestId;

    @NotNull(message = "Test Name Should Be Provided")
    private String testName;

//    @NotNull(message = "Test Id Should Be Provided")
    private String testId;

    private Timestamp createdOn;

    public EduthrillTestDTO(){

    }

    public EduthrillTestDTO(String testName, String testId){
        this.testName = testName;
        this.testId = testId;
    }

    public int getEduthrillTestId() {
        return eduthrillTestId;
    }

    public void setEduthrillTestId(int eduthrillTestId) {
        this.eduthrillTestId = eduthrillTestId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
