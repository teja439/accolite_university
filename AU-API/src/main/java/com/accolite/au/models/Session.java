package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Session {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sessionId;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    // 1 session will have 1 trainer
    @OneToOne(targetEntity = Trainer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerId")
    private Trainer trainer;

    private String sessionName;
    private Date startDate;
    private Date endDate;
    private String daySlot; // M or A
    private String classroomTopicId;
    private String classroomTopicName;
    private String calendarInviteLink;
    private Timestamp calendarInviteTMSTP;
    private Timestamp emailInviteTMSTP;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
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

    public String getCalendarInviteLink() {
        return calendarInviteLink;
    }

    public void setCalendarInviteLink(String calendarInviteLink) {
        this.calendarInviteLink = calendarInviteLink;
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
