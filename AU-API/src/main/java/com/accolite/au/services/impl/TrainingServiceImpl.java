package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.TrainingDTO;
import com.accolite.au.embeddables.TrainingEmbeddableId;
import com.accolite.au.mappers.TrainingMapper;
import com.accolite.au.models.Training;
import com.accolite.au.models.Session;
import com.accolite.au.models.Student;
import com.accolite.au.repositories.TrainingRepository;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.repositories.StudentRepository;
import com.accolite.au.services.TrainingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final SessionRepository sessionRepository;
    private final EntityManager entityManager;
    private final StudentRepository studentRepository;
    private final TrainingMapper trainingMapper;

    public TrainingServiceImpl(TrainingRepository trainingRepository, SessionRepository sessionRepository,
                               EntityManager entityManager, StudentRepository studentRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.sessionRepository = sessionRepository;
        this.entityManager = entityManager;
        this.studentRepository = studentRepository;
        this.trainingMapper = trainingMapper;
    }

    @Override
    public TrainingDTO markAndUpdateTrainingData(TrainingDTO trainingDTO, char type) {
        if(sessionRepository.existsById(trainingDTO.getAttendanceId().getSessionId()) &&
                studentRepository.existsById(trainingDTO.getAttendanceId().getStudentId())){

            TrainingEmbeddableId trainingEmbeddableId = new TrainingEmbeddableId(trainingDTO.getAttendanceId().getSessionId(), trainingDTO.getAttendanceId().getStudentId());
            Training training;
            if(trainingRepository.existsById(trainingEmbeddableId)){
                training = trainingRepository.getOne(trainingEmbeddableId);
                if(type == 'M' || type == 'm'){
                    training.setMarks(trainingDTO.getMarks());
                }
                else{
                    training.setStatus(trainingDTO.getStatus());
                }
            }
            else {
                // if record doesn't already exists
                training = trainingMapper.toTraining(trainingDTO);
                Student student = entityManager.getReference(Student.class, trainingDTO.getAttendanceId().getStudentId());
                Session session = entityManager.getReference(Session.class, trainingDTO.getAttendanceId().getSessionId());
                training.setSession(session);
                training.setStudent(student);
            }
            return trainingMapper.toTrainingDTO(trainingRepository.saveAndFlush(training));
        }
        else if(!sessionRepository.existsById(trainingDTO.getAttendanceId().getSessionId())){
            throw new CustomEntityNotFoundExceptionDTO("Session with id : " + trainingDTO.getAttendanceId().getSessionId() + " not Found");
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + trainingDTO.getAttendanceId().getStudentId() + " not Found");
    }

    @Override
    public TrainingDTO getTrainingData(int sessionId, int studentId){
        if(sessionRepository.existsById(sessionId) && studentRepository.existsById(studentId)){
            return trainingMapper.toTrainingDTO(trainingRepository.getOne(new TrainingEmbeddableId(sessionId, studentId)));
        }
        else if(!sessionRepository.existsById(sessionId)){
            throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionId + " not Found");
        }
        throw new CustomEntityNotFoundExceptionDTO("Student with id : " + studentId + " not Found");
    }

	@Override
    public ObjectNode getAllTrainingData(char type, int batchId){
        // GENERATING CUSTOM ATTENDANCE RESPONSE

        // Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        // create a ObjectNode root Node
        ObjectNode rootNode = mapper.createObjectNode();

        List<String[]> sessionsReport ;

//        if(type == 'A' || type == 'a'){
//            sessionsReport = trainingRepository.findAllSessionsAttendeesCount(); // [[total_present_count, 1, 'session1'], [total_present_count, 2, 'session2']]
//        }
//        else{
//            sessionsReport = trainingRepository.findAllSessionsMarks(); // [[avg_marks, 1, 'session1'], [avg_marks, 2, 'session2']]
//        }

        //sessionsReport = sessionRepository.findAllSessions();
        sessionsReport = sessionRepository.findAllSessionsByBatchId(batchId);
        
        ArrayNode sessionNode = mapper.createArrayNode();

        for(String[] row: sessionsReport){

            ObjectNode tempSessionEntity = mapper.createObjectNode();
            tempSessionEntity.put("sessionId", row[1]);
            tempSessionEntity.put("sessionName", row[0]);
            sessionNode.add(tempSessionEntity);
        }

        rootNode.set("sessions", sessionNode);

        ArrayNode attendanceDataNode = mapper.createArrayNode();

        for (Student student : studentRepository.findAllByBatch_BatchIdOrderByFirstNameAsc(batchId)) {
            List<String[]> tempSessions = trainingRepository.findAllSessionsForStudent(student.getStudentId()); // [['session1', 'A', 12], ['session2', 'P', 45]]

            ObjectNode tempEntity = mapper.createObjectNode();

            // Creating Student JSONObject
            ObjectNode tempStudentEntity = mapper.createObjectNode();
            tempStudentEntity.put("studentName", student.getFirstName() + " " + student.getLastName());
            tempStudentEntity.put("studentEmail", student.getEmailId());
            tempStudentEntity.put("studentFirstName", student.getFirstName());
            tempStudentEntity.put("studentLastName", student.getLastName());
            tempStudentEntity.put("studentId", student.getStudentId());
            tempEntity.put("student", tempStudentEntity.toString());

            tempEntity.set("student", tempStudentEntity);

            for (String[] row : tempSessions) {
                ObjectNode tempSessionEntity = mapper.createObjectNode();
                tempSessionEntity.put("sessionName", row[1]);
                if(type == 'M' || type == 'm') {
                    tempSessionEntity.put("marks", row[3]);
                }
                else{
                    tempSessionEntity.put("attendance", row[2]);
                }
                tempEntity.set(row[0], tempSessionEntity);
            }

            attendanceDataNode.add(tempEntity);
        }

        if(type == 'M' || type == 'm') {
            rootNode.set("marksData", attendanceDataNode);
        }
        else{
            rootNode.set("attendanceData", attendanceDataNode);
        }
        return rootNode;
    }

}
