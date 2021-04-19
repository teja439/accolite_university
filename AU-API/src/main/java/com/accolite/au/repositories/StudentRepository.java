package com.accolite.au.repositories;

import com.accolite.au.dto.StudentDTO;
import com.accolite.au.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByBatch_BatchIdOrderByFirstNameAsc(int batchId);

    @Query("SELECT s from Student s where s.batch.batchId = :batchId and s.studentGroup IS NULL")
    List<Student> findAllByBatch_BatchIdWhereStudentGroupIsNull(int batchId);

    Student findStudentByEmailId(String email);

    @Query("SELECT s from Student s where s not in (:studentList) and s.studentGroup IS NULL")
    List<Student> findAllByStudentInStudentsList(@Param("studentList") List<Student> studentList);

    @Query("SELECT student.location as location, COUNT(student) as studentPerLocation from Student as student where student.batch.batchId = :batchId group by student.location")
    List<Map<String, Object>> getAllStudentsCountPerLocation(@Param("batchId") int batchId);
}