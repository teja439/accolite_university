package com.accolite.au.models;

import com.accolite.au.embeddables.TrainingEmbeddableId;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Training implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO) // --> auto generated key
    private int id;

    // Composite Key
    @EmbeddedId
    private TrainingEmbeddableId attendanceId;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @MapsId("sessionId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    public TrainingEmbeddableId getAttendanceEmbeddableId() {
        return attendanceId;
    }

    public void setAttendanceEmbeddableId(TrainingEmbeddableId attendanceId) {
        this.attendanceId = attendanceId;
    }

    private String status;

    private int marks;


    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdOn;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainingEmbeddableId getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(TrainingEmbeddableId attendanceId) {
        this.attendanceId = attendanceId;
    }

}
