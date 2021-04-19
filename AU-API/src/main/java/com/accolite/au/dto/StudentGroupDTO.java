package com.accolite.au.dto;

import com.accolite.au.models.Batch;
import com.accolite.au.models.Student;
import com.accolite.au.models.Trainer;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class StudentGroupDTO {
    private int studentGroupId;
    private int batchId;
    private Set<Student> students;
    private Set<StudentDTO> studentsList;

    @NotNull(message = "Group Name Should Be Provided")
    private String studentGroupName;

    private String projectDocUrl;
    private String projectName;
    private String groupFeedback;

    private int trainerId;

    @CreationTimestamp
    private Timestamp createdOn;

    public String getProjectDocUrl() {
        return projectDocUrl;
    }

    public void setProjectDocUrl(String projectDocUrl) {
        this.projectDocUrl = projectDocUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getGroupFeedback() {
        return groupFeedback;
    }

    public void setGroupFeedback(String groupFeedback) {
        this.groupFeedback = groupFeedback;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<StudentDTO> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(Set<StudentDTO> studentsList) {
        this.studentsList = studentsList;
    }

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getStudentGroupName() {
        return studentGroupName;
    }

    public void setStudentGroupName(String studentGroupName) {
        this.studentGroupName = studentGroupName;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public StudentGroupDTO(){

    }

    public StudentGroupDTO(int batchId, Set<Student> students, @NotNull(message = "Group Name Should Be Provided") String studentGroupName, int trainerId) {
        this.batchId = batchId;
        this.students = students;
        this.studentGroupName = studentGroupName;
        this.trainerId = trainerId;
    }
}
