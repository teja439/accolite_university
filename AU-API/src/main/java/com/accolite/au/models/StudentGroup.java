package com.accolite.au.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudentGroup implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentGroupId;

    private String studentGroupName;
    private String groupFeedback;
    private String projectName;
    private String projectDocUrl;

    @ManyToOne
    @JoinColumn(name = "batchId")
    private Batch batch;

    @OneToMany(targetEntity = Student.class, mappedBy = "studentGroup", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();

    @OneToMany(targetEntity = ProjectFeedback.class, mappedBy = "studentGroup", fetch = FetchType.LAZY)
    private Set<ProjectFeedback> projectFeedbacks = new HashSet<>();

    @OneToOne(targetEntity = Trainer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainerId")
    private Trainer trainer;

    @CreationTimestamp
    private Timestamp createdOn;

    public Set<ProjectFeedback> getProjectFeedbacks() {
        return projectFeedbacks;
    }

    public void setProjectFeedbacks(Set<ProjectFeedback> projectFeedbacks) {
        this.projectFeedbacks = projectFeedbacks;
    }

    public String getProjectDocUrl() {
        return projectDocUrl;
    }

    public void setProjectDocUrl(String projectDocUrl) {
        this.projectDocUrl = projectDocUrl;
    }

    public String getGroupFeedback() {
        return groupFeedback;
    }

    public void setGroupFeedback(String groupFeedback) {
        this.groupFeedback = groupFeedback;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public String getStudentGroupName() {
        return studentGroupName;
    }

    public void setStudentGroupName(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }
}
