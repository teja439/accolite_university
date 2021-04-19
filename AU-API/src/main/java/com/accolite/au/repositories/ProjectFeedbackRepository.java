package com.accolite.au.repositories;

import com.accolite.au.models.ProjectFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectFeedbackRepository extends JpaRepository<ProjectFeedback, Integer> {
    @Query("SELECT s from ProjectFeedback as s WHERE s.student.studentId = :studentId")
    ProjectFeedback containsStudentFeedback(int studentId);
}
