package com.accolite.au.repositories;

import com.accolite.au.embeddables.TrainingEmbeddableId;
import com.accolite.au.models.Training;
import com.accolite.au.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface TrainingRepository extends JpaRepository<Training, TrainingEmbeddableId> {

    @Query("SELECT DISTINCT student FROM Training")
    List<Student> findAllDistinctStudents();

    @Query("SELECT session.sessionId, session.sessionName, status, marks FROM Training WHERE student.studentId = :studentId")
    List<String[]> findAllSessionsForStudent(@Param("studentId") int studentId);

    @Query("SELECT avg (marks) FROM Training WHERE student.studentId = :studentId")
    Double findAllSessionsForStudentAnalysis(@Param("studentId") int studentId);


    @Query("SELECT AVG(t.marks), t.session.sessionId, t.session.sessionName FROM Training AS t GROUP BY t.session.sessionId")
    List<String[]> findAllSessionsMarks();

    @Query("SELECT COUNT(t.session.sessionId), t.session.sessionId, t.session.sessionName FROM Training AS t WHERE t.status = 'P' GROUP BY t.session.sessionId")
    List<String[]> findAllSessionsAttendeesCount();

    @Query("SELECT t.student.studentId, COUNT(t) FROM Training AS t WHERE t.status = 'P' GROUP BY t.student.studentId")
    List<Map<Integer, Integer>> findSessionsAttendancePerStudent();
    
    @Query("SELECT COUNT(t) FROM Training AS t WHERE t.status = 'P' AND t.student.studentId = :studentId")
    Integer findSessionsAttendancePerStudent(int studentId);

    // JOIN QUERY
    //    SELECT s.session_name, s.session_id, tr.total_atten FROM session as s LEFT JOIN (SELECT COUNT(t.session_session_id) as total_atten, t.session_session_id FROM training as t WHERE t.status = 'P' GROUP BY t.session_session_id) as tr ON s.session_id = tr.session_session_id;

}
