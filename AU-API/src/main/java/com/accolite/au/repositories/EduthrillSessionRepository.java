package com.accolite.au.repositories;

import com.accolite.au.models.EduthrillSession;
import com.accolite.au.models.EduthrillTest;
import com.accolite.au.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EduthrillSessionRepository extends JpaRepository<EduthrillSession, Integer> {
    @Query("SELECT avg(eduSession.marks) from EduthrillSession as eduSession where eduSession.student.studentId = :studentId")
    Double findFollowingEduthrillSessionsAverage(@Param("studentId") int studentId);

    EduthrillSession findByStudentAndEduthrillTest(Student student, EduthrillTest eduthrillTest);
}
