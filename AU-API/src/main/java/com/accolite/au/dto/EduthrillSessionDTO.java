package com.accolite.au.dto;

import com.accolite.au.models.EduthrillTest;
import com.accolite.au.models.Student;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

public class EduthrillSessionDTO {
    private int eduthrillSessionId;

    private double marks;
    private int studentId;
    private int eduthrillTestId;

    private Timestamp timestamp;

    public EduthrillSessionDTO(){

    }

    public EduthrillSessionDTO(int studentId, int eduthrillTestId, double marks){
        this.studentId = studentId;
        this.eduthrillTestId = eduthrillTestId;
        this.marks = marks;
    }

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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEduthrillTestId() {
        return eduthrillTestId;
    }

    public void setEduthrillTestId(int eduthrillTestId) {
        this.eduthrillTestId = eduthrillTestId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
