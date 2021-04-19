package com.accolite.au.models;

import javax.persistence.*;

@Entity
public class ProjectFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackId;

    @OneToOne(fetch = FetchType.LAZY)
    private Student student;

    private int marks;
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "studentGroupId")
    private StudentGroup studentGroup;

    public StudentGroup getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(StudentGroup studentGroup) {
        this.studentGroup = studentGroup;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "ProjectFeedback{" +
                "feedbackId=" + feedbackId +
                ", student=" + student.getStudentId() +
                ", marks=" + marks +
                ", feedback='" + feedback + '\'' +
                ", studentGroup=" + studentGroup.getStudentGroupId() +
                '}';
    }
}
