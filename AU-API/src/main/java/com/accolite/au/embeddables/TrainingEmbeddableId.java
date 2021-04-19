package com.accolite.au.embeddables;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TrainingEmbeddableId implements Serializable {
    private int sessionId;
    private int studentId;


    public TrainingEmbeddableId(int sessionId, int studentId) {
        this.sessionId = sessionId;
        this.studentId = studentId;
    }

    public TrainingEmbeddableId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingEmbeddableId attendanceEmbeddableId = (TrainingEmbeddableId) o;
        return sessionId == attendanceEmbeddableId.sessionId &&
                studentId == attendanceEmbeddableId.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId, studentId);
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}