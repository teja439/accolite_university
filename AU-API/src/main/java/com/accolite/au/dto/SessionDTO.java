package com.accolite.au.dto;

import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

public class SessionDTO {

    public SessionDTO(){

    }

    public SessionDTO(@NotNull(message = "sessionName Should be provided") String sessionName, String classroomTopicId, @NotNull(message = "startDate Should be provided") Date startDate, Date endDate, String daySlot) {
        this.sessionName = sessionName;
        this.classroomTopicId = classroomTopicId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daySlot = daySlot;
    }

    public SessionDTO(int batchId, String sessionName, String startDate, String daySlot, TrainerDTO trainerDTO) {
        this.sessionName = sessionName;
        this.startDate = Date.valueOf(startDate);
        this.daySlot = daySlot;
        this.trainer = trainerDTO;
        this.batchId = batchId;
    }
    

    private int sessionId;
    private int batchId;
    private TrainerDTO trainer;

    @NotNull(message = "sessionName Should be provided")
    private String sessionName;

    private String daySlot;
    private String classroomTopicId;
    private String classroomTopicName;
    private String calenderInviteLink;
    private Timestamp calendarInviteTMSTP;
    private Timestamp emailInviteTMSTP;

    @NotNull(message = "startDate Should be provided")
    private Date startDate;

    private Date endDate;

    @CreationTimestamp
    private Timestamp createdOn;


    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public TrainerDTO getTrainer() {
        return trainer;
    }

    public void setTrainer(TrainerDTO trainer) {
        this.trainer = trainer;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getDaySlot() {
        return daySlot;
    }

    public void setDaySlot(String daySlot) {
        this.daySlot = daySlot;
    }

    public String getClassroomTopicId() {
        return classroomTopicId;
    }

    public void setClassroomTopicId(String classroomTopicId) {
        this.classroomTopicId = classroomTopicId;
    }

    public String getClassroomTopicName() {
        return classroomTopicName;
    }

    public void setClassroomTopicName(String classroomTopicName) {
        this.classroomTopicName = classroomTopicName;
    }

    public String getCalenderInviteLink() {
        return calenderInviteLink;
    }

    public void setCalenderInviteLink(String calenderInviteLink) {
        this.calenderInviteLink = calenderInviteLink;
    }

    public Timestamp getCalendarInviteTMSTP() {
        return calendarInviteTMSTP;
    }

    public void setCalendarInviteTMSTP(Timestamp calendarInviteTMSTP) {
        this.calendarInviteTMSTP = calendarInviteTMSTP;
    }

    public Timestamp getEmailInviteTMSTP() {
        return emailInviteTMSTP;
    }

    public void setEmailInviteTMSTP(Timestamp emailInviteTMSTP) {
        this.emailInviteTMSTP = emailInviteTMSTP;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
