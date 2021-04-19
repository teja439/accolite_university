package com.accolite.au.controller;

import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.StudentDTO;
import com.accolite.au.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping({"/add"})
    public ResponseEntity<StudentDTO> addStudentToBatch(@Valid @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity(studentService.addOrUpdateStudentToBatch(studentDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = {"/bulkAdd"})
    public ResponseEntity<SuccessResponseDTO> addBulkStudents(@RequestParam(name = "studentsFile") MultipartFile studentFile, @RequestParam(name="batchId") int batchId) throws IOException{
        studentService.uploadFile(studentFile, batchId);
        return new ResponseEntity(new SuccessResponseDTO("File will be uploaded soon, and you can refresh the page to see the updated data !!", HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping({"/update"})
    public ResponseEntity<StudentDTO> updateStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return new ResponseEntity(studentService.addOrUpdateStudentToBatch(studentDTO), HttpStatus.CREATED);
    }

    @GetMapping({"/all"})
    public ResponseEntity<List<StudentDTO>> getAllStudentsForABatch(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(studentService.getAllStudentsForABatch(batchId), HttpStatus.OK);
    }

    @GetMapping({"/allPerLocation"})
    public ResponseEntity<List<Map<String, Object>>> getAllStudentsCountPerLocation(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(studentService.getAllStudentsCountPerLocation(batchId), HttpStatus.OK);
    }

    @GetMapping({"/allUnassigned"})
    public ResponseEntity<List<StudentDTO>> getAllUnassignedStudentsForABatch(@RequestParam(required = true, name = "batchId") int batchId){
        return new ResponseEntity(studentService.getAllUnassignedStudentsForABatch(batchId), HttpStatus.OK);
    }

    @GetMapping({"/{studentId}"})
    public ResponseEntity<StudentDTO> getStudent(@PathVariable(required = true, name = "studentId") int studentId){
        return new ResponseEntity(studentService.getStudent(studentId), HttpStatus.OK);
    }

    @DeleteMapping({"/{studentId}"})
    public ResponseEntity<SuccessResponseDTO> deleteStudent(@PathVariable(required = true, name="studentId") int studentId){
        return new ResponseEntity(studentService.deleteStudent(studentId), HttpStatus.OK);
    }
}
