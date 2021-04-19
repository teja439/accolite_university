package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Student implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;

    @ManyToOne
    @JoinColumn(name = "batchId")
    private Batch batch;

    @ManyToOne
    @JoinColumn(name = "studentGroupId")
    private StudentGroup studentGroup;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProjectFeedback projectFeedback;

    @OneToMany(targetEntity = EduthrillSession.class, mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EduthrillSession> eduthrillSessions;

    private String firstName;
    private String lastName;
    private String skypeId;
    private String emailId;
    private String location;

    @CreationTimestamp
    private Timestamp createdOn;

    public Set<EduthrillSession> getEduthrillSessions() {
        return eduthrillSessions;
    }

    public void setEduthrillSession(Set<EduthrillSession> eduthrillSessions) {
        this.eduthrillSessions = eduthrillSessions;
    }

    public ProjectFeedback getProjectFeedback() {
        return projectFeedback;
    }

    public void setProjectFeedback(ProjectFeedback projectFeedback) {
        this.projectFeedback = projectFeedback;
    }

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

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

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
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
}
