package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class EduthrillTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eduthrillTestId;

    private String testName;
    private String testId;

    @OneToMany(targetEntity = EduthrillSession.class, mappedBy = "eduthrillTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<EduthrillSession> eduthrillSessions;

    @CreationTimestamp
    private Timestamp createdOn;

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
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

//    public EduthrillSession getEduthrillSession() {
//        return eduthrillSession;
//    }
//
//    public void setEduthrillSession(EduthrillSession eduthrillSession) {
//        this.eduthrillSession = eduthrillSession;
//    }


    public Set<EduthrillSession> getEduthrillSessions() {
        return eduthrillSessions;
    }

    public void setEduthrillSessions(Set<EduthrillSession> eduthrillSessions) {
        this.eduthrillSessions = eduthrillSessions;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
