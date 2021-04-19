package com.accolite.au.mappers;

import com.accolite.au.dto.TrainerDTO;
import com.accolite.au.models.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    
    @Mapping(source = "trainerDTO.businessUnit.buId", target = "businessUnit.buId")
    Trainer toTrainer(TrainerDTO trainerDTO);
    
    @Mapping(source = "trainer.businessUnit", target = "businessUnit")
    TrainerDTO toTrainerDTO(Trainer trainer);

    
    List<TrainerDTO> toTrainerDTOs(List<Trainer> trainers);

    Set<TrainerDTO> toTrainerDTOsSet(Set<Trainer> trainers);

}
