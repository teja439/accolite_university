package com.accolite.au.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class BusinessUnitDTO {
    private int buId;

    @NotNull(message = "BU Name Should be provided")
    private String buName;

    @NotNull(message = "BU Head Name Should be provided")
    private String buHeadName;

    @Email
    private String buHeadEmail;

    private Timestamp createdOn;

    public int getBuId() {
        return buId;
    }

    public void setBuId(int buId) {
        this.buId = buId;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getBuHeadName() {
        return buHeadName;
    }

    public void setBuHeadName(String buHeadName) {
        this.buHeadName = buHeadName;
    }

    public String getBuHeadEmail() {
        return buHeadEmail;
    }

    public void setBuHeadEmail(String buHeadEmail) {
        this.buHeadEmail = buHeadEmail;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public BusinessUnitDTO(){

    }

    public BusinessUnitDTO(String buName, String buHeadName, String buHeadEmail) {
        this.buName = buName;
        this.buHeadName = buHeadName;
        this.buHeadEmail = buHeadEmail;
    }
}
