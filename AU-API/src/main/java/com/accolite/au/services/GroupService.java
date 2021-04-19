package com.accolite.au.services;

import com.accolite.au.dto.*;
import com.accolite.au.models.ProjectFeedback;
import com.accolite.au.models.Student;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public interface GroupService {
    StudentGroupDTO addGroup(StudentGroupDTO studentGroupDTO, int batchId);

    StudentGroupDTO updateGroup(StudentGroupDTO studentGroupDTO, int groupId);

    StudentGroupDTO getGroup(int batchId);

    List<StudentGroupDTO> automateGrouping(int batchId, List<Student> neglectStudentsList, int groupSize) throws CustomEntityNotFoundExceptionDTO;

    List<StudentGroupDTO> getAllGroupsForABatch(int batchId);

    SuccessResponseDTO deleteGroup(int batchId);

    SuccessResponseDTO deleteStudentFromGroup(int groupId, int studentId) throws CustomEntityNotFoundExceptionDTO;

    StudentGroupDTO addStudentToGroup(int groupId, List<Student> selectedStudentsList) throws CustomEntityNotFoundExceptionDTO;

    FeedbackDTO submitFeedback(FeedbackDTO feedbackDTO, int groupId);

    FeedbackDTO getAllFeedbacks(int groupId);

    ProjectFeedbackDTO getFeedback(int feedbackId);

    ObjectNode getAllFinalEvaluationData(int batchId);

    ProjectFeedbackDTO assignMarks(ProjectFeedbackDTO projectFeedbackDTO);
}
