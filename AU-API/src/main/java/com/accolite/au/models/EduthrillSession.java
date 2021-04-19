package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class EduthrillSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int eduthrillSessionId;

    private double marks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eduthrillTestId")
    private EduthrillTest eduthrillTest;

    @CreationTimestamp
    private Timestamp timestamp;

    public int getEduthrillSessionId() {
        return eduthrillSessionId;
    }

    public void setEduthrillSessionId(int eduthrillSessionId) {
        this.eduthrillSessionId = eduthrillSessionId;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public EduthrillTest getEduthrillTest() {
        return eduthrillTest;
    }

    public void setEduthrillTest(EduthrillTest eduthrillTest) {
        this.eduthrillTest = eduthrillTest;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
