package com.accolite.au.services;

import com.accolite.au.dto.StudentDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDTO> getAllStudentsForABatch(int batchId);

    List<StudentDTO> getAllUnassignedStudentsForABatch(int batchId);

    StudentDTO getStudent(int studentId);

    StudentDTO addOrUpdateStudentToBatch(StudentDTO studentDTO);

    SuccessResponseDTO deleteStudent(int studentId);

//    @Async
    void uploadFile(MultipartFile studentFile, int batchId);

    List<Map<String, Object>> getAllStudentsCountPerLocation(int batchId);
}
