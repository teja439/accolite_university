package com.accolite.au.services.impl;

import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.mappers.TrainerMapper;
import com.accolite.au.models.BusinessUnit;
import com.accolite.au.models.Trainer;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.repositories.BusinessUnitRepository;
import com.accolite.au.repositories.TrainerRepository;
import com.accolite.au.services.TrainerService;
import org.apache.commons.collections.map.TypedMap;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrainerServiceImpl implements TrainerService {
    
    private final BatchRepository batchRepository;
    private final TrainerRepository trainerRepository;
    private final TrainerMapper trainerMapper;
    private final EntityManager entityManager;
    private final BusinessUnitRepository businessUnitRepository;

    public TrainerServiceImpl(BatchRepository batchRepository, TrainerRepository trainerRepository, TrainerMapper trainerMapper, EntityManager entityManager, BusinessUnitRepository businessUnitRepository) {
        this.batchRepository = batchRepository;
        this.trainerRepository = trainerRepository;
        this.trainerMapper = trainerMapper;
        this.entityManager = entityManager;
        this.businessUnitRepository = businessUnitRepository;
    }

    @Override
    public TrainerDTO addToBatchOrUpdateTrainer(TrainerDTO trainerDTO){
        Trainer trainer = trainerMapper.toTrainer(trainerDTO);
        if(trainerDTO.getBusinessUnit() != null){
            BusinessUnit businessUnitReference = entityManager.getReference(BusinessUnit.class, trainerDTO.getBusinessUnit().getBuId());
            trainer.setBusinessUnit(businessUnitReference);
            return trainerMapper.toTrainerDTO(trainerRepository.saveAndFlush(trainer));
        }
        throw new CustomEntityNotFoundExceptionDTO("Business Unit Not Found");
    }

    @Override
    public List<TrainerDTO> getAllTrainers(){
        return trainerMapper.toTrainerDTOs(trainerRepository.findAll());
    }

    @Override
    public TrainerDTO getTrainer(int trainerId){
        if(trainerRepository.existsById(trainerId)) {
            Trainer trainer = trainerRepository.getOne(trainerId);
            return trainerMapper.toTrainerDTO(trainer);
        }
        throw new CustomEntityNotFoundExceptionDTO("Trainer with id : " + trainerId + " not Found");
    }

    @Override
    public SuccessResponseDTO deleteTrainer(int trainerId){
        if(trainerRepository.existsById(trainerId)) {
            trainerRepository.deleteById(trainerId);
            return new SuccessResponseDTO("Trainer with id : " + trainerId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Trainer with id : " + trainerId + " not Found");
    }

    @Override
    public List<Map<String, Object>> getAllTrainerPerBUCount(){
        List<Object[]> res = entityManager.createQuery("SELECT bU.buId as bUId, bU.buName as buName, COUNT(trainer.businessUnit.buId) as trainerPerBU from Trainer as trainer RIGHT JOIN BusinessUnit as bU on trainer.businessUnit.buId = bU.buId group by bU.buId").getResultList();
        List<Map<String, Object>> trainerPerBUCount = new ArrayList();
        for(Object obj[]: res){
            Map<String, Object> tempMap = new HashMap();
            tempMap.put("buId", obj[0]);
            tempMap.put("buName", obj[1]);
            tempMap.put("trainerPerBU", obj[2]);
            trainerPerBUCount.add(tempMap);
        }
        return trainerPerBUCount;
    }
}
