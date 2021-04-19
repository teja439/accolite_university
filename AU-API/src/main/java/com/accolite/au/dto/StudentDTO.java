package com.accolite.au.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class StudentDTO {
    private int studentId;
    private int batchId;

    @NotNull(message = "firstName Should be provided")
    private String firstName;

    @NotNull(message = "lastName Should be provided")
    private String lastName;

    private String skypeId;
    private String location;

    @Email
    @NotNull(message = "emailId Should be provided")
    private String emailId;

    private Timestamp createdOn;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public StudentDTO(){

    }

    public StudentDTO(String firstName, String lastName, String emailId, String skypeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skypeId = skypeId;
        this.emailId = emailId;
    }
}
