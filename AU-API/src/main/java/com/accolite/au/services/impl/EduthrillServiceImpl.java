package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.EduthrillSessionDTO;
import com.accolite.au.dto.EduthrillTestDTO;
import com.accolite.au.mappers.EduthrillSessionMapper;
import com.accolite.au.mappers.EduthrillTestMapper;
import com.accolite.au.models.EduthrillSession;
import com.accolite.au.models.EduthrillTest;
import com.accolite.au.models.Student;
import com.accolite.au.repositories.EduthrillSessionRepository;
import com.accolite.au.repositories.EduthrillTestRepository;
import com.accolite.au.repositories.StudentRepository;
import com.accolite.au.services.EduthrillService;
import com.accolite.au.services.GroupService;
import com.accolite.au.utils.ValidatorFunctions;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class EduthrillServiceImpl implements EduthrillService {
    private final StudentRepository studentRepository;
    private final EduthrillTestRepository eduthrillTestRepository;
    private final EduthrillSessionRepository eduthrillSessionRepository;
    private final EduthrillSessionMapper eduthrillSessionMapper;
    private final EduthrillTestMapper eduthrillTestMapper;
    private final GroupService groupService;
    private final ValidatorFunctions validatorFunctions;

    public EduthrillServiceImpl(StudentRepository studentRepository, EduthrillTestRepository eduthrillTestRepository, EduthrillSessionRepository eduthrillSessionRepository, EduthrillSessionMapper eduthrillSessionMapper, EduthrillTestMapper eduthrillTestMapper, GroupService groupService, ValidatorFunctions validatorFunctions) {
        this.studentRepository = studentRepository;
        this.eduthrillTestRepository = eduthrillTestRepository;
        this.eduthrillSessionRepository = eduthrillSessionRepository;
        this.eduthrillSessionMapper = eduthrillSessionMapper;
        this.eduthrillTestMapper = eduthrillTestMapper;
        this.groupService = groupService;
        this.validatorFunctions = validatorFunctions;
    }

    private boolean isValidRow(String[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].compareTo("") == 0) {
                return false;
            }
            if (i == 2) {
                if (validatorFunctions.emailValidator(row[i]) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean uploadTestDetails(String studentEmail, double successPercentage, EduthrillTest eduthrillTest){
        Student student = studentRepository.findStudentByEmailId(studentEmail);
        if(student != null){
            if(eduthrillSessionRepository.findByStudentAndEduthrillTest(student, eduthrillTest) == null) {
                EduthrillSessionDTO eduthrillSessionDTO = new EduthrillSessionDTO(student.getStudentId(), eduthrillTest.getEduthrillTestId(), successPercentage);
                EduthrillSession eduthrillSession = eduthrillSessionMapper.toEduthrillSession(eduthrillSessionDTO);
                //System.out.println("1 " + eduthrillSession.getStudent() + " " + eduthrillSession.getEduthrillTest());
                eduthrillSession = eduthrillSessionRepository.saveAndFlush(eduthrillSession);
                //System.out.println("2 " + eduthrillSession.getStudent() + " " + eduthrillSession.getEduthrillTest());

                student.getEduthrillSessions().add(eduthrillSession);
                studentRepository.saveAndFlush(student);
                //System.out.println("3 " + student.getEduthrillSessions());

                if(eduthrillTest.getEduthrillSessions() != null){
                    eduthrillTest.getEduthrillSessions().add(eduthrillSession);
                }
                else{
                    eduthrillTest.setEduthrillSessions(new HashSet<>(Arrays.asList(eduthrillSession)));
                }
                eduthrillTestRepository.saveAndFlush(eduthrillTest);
                //System.out.println("4 " + eduthrillTest.getEduthrillSessions());
                return true;
            }
        }
        return false;
    }

    @Override
    public ObjectNode uploadTest(MultipartFile sessionsFile, EduthrillTestDTO eduthrillTestDTO, int batchId){
        EduthrillTest eduthrillTest;
        if(eduthrillTestRepository.ifTestExists(eduthrillTestDTO.getTestId(), eduthrillTestDTO.getTestName()) == null) {
            eduthrillTest = eduthrillTestRepository.saveAndFlush(eduthrillTestMapper.toEduthrillTest(eduthrillTestDTO));
        }
        else {
            eduthrillTest = eduthrillTestRepository.ifTestExists(eduthrillTestDTO.getTestId(), eduthrillTestDTO.getTestName());
        }
        try {
            InputStreamReader reader = new InputStreamReader(sessionsFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
            for (String[] row : rows) {
                if (this.isValidRow(row)) {
                    this.uploadTestDetails(row[2], Double.parseDouble(row[3]), eduthrillTest);
                } else {
                    System.out.println("Validation Error, Wrong Row Format");
                }
            }
        } catch (IOException e) {
            System.out.println("IOException Exception Occurred!! " + e.toString());
        } catch (CsvException e) {
            System.out.println("CSV Exception Occurred!! " + e.toString());
        }

        return groupService.getAllFinalEvaluationData(batchId);
    }

    @Override
    public EduthrillTestDTO getEduthrillTest(int eduthrillTestId){
        if(eduthrillTestRepository.existsById(eduthrillTestId)){
            return eduthrillTestMapper.toEduthrillTestDTO(eduthrillTestRepository.getOne(eduthrillTestId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Eduthrill Test Not Found");
    }

    @Override
    public List<EduthrillTestDTO> getAllEduthrillTest(){
        return eduthrillTestMapper.toEduthrillTestDTOs(eduthrillTestRepository.findAll());
    }

    @Override
    public List<EduthrillSessionDTO> getAllEduthrillSessionsForTest(int eduthrillTestId){
        if(eduthrillTestRepository.existsById(eduthrillTestId)){
            return eduthrillSessionMapper.toEduthrillSessionDTOsList(eduthrillTestRepository.getOne(eduthrillTestId).getEduthrillSessions());
        }
        throw new CustomEntityNotFoundExceptionDTO("Eduthrill Test Not Found");
    }

    @Override
    public EduthrillSessionDTO getEduthrillSession(int eduthrillSessionId){
        if(eduthrillSessionRepository.existsById(eduthrillSessionId)){
            return eduthrillSessionMapper.toEduthrillSessionDTO(eduthrillSessionRepository.getOne(eduthrillSessionId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Eduthrill Session Not Found");
    }

    @Override
    public List<EduthrillSessionDTO> getAllEduthrillSessionsForStudent(int studentId){
        if(studentRepository.existsById(studentId)){
            return eduthrillSessionMapper.toEduthrillSessionDTOsList(studentRepository.getOne(studentId).getEduthrillSessions());
        }
        throw new CustomEntityNotFoundExceptionDTO("Student Not Found");
    }
}
