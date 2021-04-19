package com.accolite.au.dto;

import com.accolite.au.models.ProjectFeedback;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class FeedbackDTO {
    @NotNull(message="Group Id Should Not Be Null")
    private int studentGroupId;

    private String groupFeedback;

    private Set<ProjectFeedbackDTO> projectFeedbackList;

    public int getStudentGroupId() {
        return studentGroupId;
    }

    public void setStudentGroupId(int studentGroupId) {
        this.studentGroupId = studentGroupId;
    }

    public String getGroupFeedback() {
        return groupFeedback;
    }

    public void setGroupFeedback(String groupFeedback) {
        this.groupFeedback = groupFeedback;
    }

    public Set<ProjectFeedbackDTO> getProjectFeedbackList() {
        return projectFeedbackList;
    }

    public void setProjectFeedbackList(Set<ProjectFeedbackDTO> projectFeedbackList) {
        this.projectFeedbackList = projectFeedbackList;
    }

    public FeedbackDTO(){

    }

    public FeedbackDTO(int groupId, String groupFeedback, Set<ProjectFeedbackDTO> projectFeedbackList){
        this.studentGroupId = groupId;
        this.groupFeedback = groupFeedback;
        this.projectFeedbackList = projectFeedbackList;
    }
}
