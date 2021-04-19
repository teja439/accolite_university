package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Batch implements Serializable {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int batchId;

    private String batchName;
    private String commonSkypeId;
    private String commonClassroomId;
    private String classroomLink;
    private String courseGroupEmail;
    private String classroomName;

    @OneToMany(targetEntity = Student.class, mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();

    @OneToMany(targetEntity = Session.class, mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Session> sessions = new HashSet<>();

    @OneToMany(targetEntity = StudentGroup.class, mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<StudentGroup> studentGroups = new HashSet<>();

    private Date startDate;

    private Date endDate;

    @CreationTimestamp
    private Timestamp createdOn;

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getCommonSkypeId() {
        return commonSkypeId;
    }

    public void setCommonSkypeId(String commonSkypeId) {
        this.commonSkypeId = commonSkypeId;
    }

    public String getCommonClassroomId() {
        return commonClassroomId;
    }

    public void setCommonClassroomId(String commonClassroomId) {
        this.commonClassroomId = commonClassroomId;
    }

    public String getClassroomLink() {
        return classroomLink;
    }

    public void setClassroomLink(String classroomLink) {
        this.classroomLink = classroomLink;
    }

    public String getCourseGroupEmail() {
        return courseGroupEmail;
    }

    public void setCourseGroupEmail(String courseGroupEmail) {
        this.courseGroupEmail = courseGroupEmail;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
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

