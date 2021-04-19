package com.accolite.au.services.impl;

import com.accolite.au.dto.*;
import com.accolite.au.mappers.BusinessUnitMapper;
import com.accolite.au.mappers.SessionMapper;
import com.accolite.au.mappers.TrainerMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.models.Session;
import com.accolite.au.models.Trainer;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.BusinessUnitRepository;
import com.accolite.au.repositories.SessionRepository;
import com.accolite.au.repositories.TrainerRepository;
import com.accolite.au.services.SessionService;
import com.accolite.au.services.TrainerService;
import com.accolite.au.utils.ValidatorFunctions;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final BatchRepository batchRepository;
    private final SessionMapper sessionMapper;
    private final EntityManager entityManager;
    private final ValidatorFunctions validatorFunctions;
    private final TrainerRepository trainerRepository;
    private final BusinessUnitRepository businessUnitRepository;
    private final TrainerService trainerService;
    private final TrainerMapper trainerMapper;
    private final BusinessUnitMapper businessUnitMapper;

    public SessionServiceImpl(SessionRepository sessionRepository, BatchRepository batchRepository, SessionMapper sessionMapper, EntityManager entityManager, ValidatorFunctions validatorFunctions, TrainerRepository trainerRepository, BusinessUnitRepository businessUnitRepository, TrainerService trainerService, TrainerMapper trainerMapper, BusinessUnitMapper businessUnitMapper) {
        this.sessionRepository = sessionRepository;
        this.batchRepository = batchRepository;
        this.sessionMapper = sessionMapper;
        this.entityManager = entityManager;
        this.validatorFunctions = validatorFunctions;
        this.trainerRepository = trainerRepository;
        this.businessUnitRepository = businessUnitRepository;
        this.trainerService = trainerService;
        this.trainerMapper = trainerMapper;
        this.businessUnitMapper = businessUnitMapper;
    }

    @Override
    public SuccessResponseDTO deleteSession(int sessionId){
        if(sessionRepository.existsById(sessionId)){
            sessionRepository.deleteById(sessionId);
            return new SuccessResponseDTO("Session with id : " + sessionId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionId + " not Found");
    }


    @Override
    public SessionDTO addOrUpdateSession(SessionDTO sessionDTO) {
        if(batchRepository.existsById(sessionDTO.getBatchId())) {
            Session session = sessionMapper.toSession(sessionDTO);
            Batch batchReference = entityManager.getReference(Batch.class, sessionDTO.getBatchId());
            Trainer trainerReference = entityManager.getReference(Trainer.class, sessionDTO.getTrainer().getTrainerId());
            session.setBatch(batchReference);
            session.setTrainer(trainerReference);
            return sessionMapper.toSessionDTO(sessionRepository.saveAndFlush(session));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + sessionDTO.getBatchId() + " not Found");
    }

    @Override
    public List<SessionDTO> getAllSessions(int batchId) {
        if(batchRepository.existsById(batchId)) {
            return sessionMapper.toSessionDTOs(sessionRepository.findAllByBatch_BatchIdOrderByStartDateAscDaySlotDesc(batchId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public SessionDTO getSession(int sessionId) {
        if (sessionRepository.existsById(sessionId)) {
            return sessionMapper.toSessionDTO(sessionRepository.getOne(sessionId));
        }
        throw new CustomEntityNotFoundExceptionDTO("Session with id : " + sessionId + " not Found");
    }

    private boolean isValidRow(String[] row){
        for(int i = 0 ; i < row.length ; i++){
            if(row[i].compareTo("") == 0){
                return false;
            }
            if(i == 5) {
                String col[] = row[i].split(",");
                for(String tempCol : col){
                    if (validatorFunctions.emailValidator(tempCol.trim()) == false) {
                        return false;
                    }
                }
                continue;
            }
            if(i == 4) {
                if (validatorFunctions.emailValidator(row[i]) == false) {
                    return false;
                }
            }
        }
        if(row.length >= 7)
            return true;

        return false;
    }

    // Finding Slots From Slot String -> 2 PM – 6 PM
    private String findSlot(String data){
        String splitData[] = data.split(" ");
        if(splitData.length >= 1){
            return splitData[1].toLowerCase().compareTo("pm") == 0 ? "A" : "M";
        }
        return "M";
    }

    // Finding Which is BU Is From this , separated column values
    private BusinessUnitDTO findWhichIsBUEmail(String row){
        String splitData[] = row.split(",");
        for(String ele : splitData){
            List<BusinessUnitDTO> bUs= businessUnitMapper.toBusinessUnitDTOs(businessUnitRepository.findAllByBuHeadEmail(ele.trim()));
            if(bUs.size() > 0){
                return bUs.get(0);
            }
        }
        return null;
    }

    // Finding Which is RM Email Is From this , seperated column values
    private String findRMEmailId(String email, String row){
        String splitData[] = row.split(",");
        for(String ele : splitData){
            if(ele.trim().compareTo(email) != 0){
                return ele.trim();
            }
        }
        return "";
    }

    @Override
    public void uploadFile(MultipartFile sessionsFile, int batchId) {
        try{
            InputStreamReader reader = new InputStreamReader(sessionsFile.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
            for(String[] row : rows){
                if(this.isValidRow(row)){
                    List<TrainerDTO> trainers = trainerMapper.toTrainerDTOs(trainerRepository.findAllByEmailId(row[4]));
                    TrainerDTO trainer;

                    // No Such Trainer Exists
                    if(trainers.size() == 0){
                        System.out.println("Creating Trainer");
                        // create trainer

                        BusinessUnitDTO bU = this.findWhichIsBUEmail(row[5]);
                        if(bU == null){
                            System.out.println("No BU Exists");
                            continue;
                        }
                        trainer = trainerService.addToBatchOrUpdateTrainer(new TrainerDTO(bU, row[3], "", this.findRMEmailId(bU.getBuHeadEmail(), row[5]), row[4]));
                        System.out.println("UFF");
                        if(trainer != null){
                            System.out.println("Added Trainer");
                        }
                        else{
                            System.out.println("Error Uploading Row as trainer can't be created");
                            continue;
                        }
                    }
                    else{
                        trainer = trainers.get(0);
                    }

                    // Create New Session
                    if(this.addOrUpdateSession(new SessionDTO(batchId, row[2], row[0], this.findSlot(row[6]), trainer)) != null){
                        System.out.println("Uploaded This Row");
                    }
                    else{
                        System.out.println("Error Uploading Row");
                    }
                }
                else{
                    System.out.println("Validation Error, Wrong Row Format");
                }
            }
        } catch (Exception e){
            System.out.println("Some Exception Occurred!! " + e.toString());
        }
    }
}
