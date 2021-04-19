package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Trainer implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int trainerId;

//    @ManyToOne
//    @JoinColumn(name = "batch_id")
//    private Batch batch;

    @OneToOne(targetEntity = BusinessUnit.class, fetch = FetchType.LAZY)
    @JoinColumn(name="buId")
    private BusinessUnit businessUnit;

    @OneToOne(fetch = FetchType.LAZY)
    private StudentGroup studentGroup;

    @OneToOne(fetch = FetchType.LAZY)
    private Session session;

    private String trainerName;
    private String skypeId;
    private String reportingManagerEmailId;
    private String emailId;

    @CreationTimestamp
    private Timestamp createdOn;

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

//    public Batch getBatch() {
//        return batch;
//    }
//
//    public void setBatch(Batch batch) {
//        this.batch = batch;
//    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getReportingManagerEmailId() {
        return reportingManagerEmailId;
    }

    public void setReportingManagerEmailId(String reportingManagerEmailId) {
        this.reportingManagerEmailId = reportingManagerEmailId;
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
}
