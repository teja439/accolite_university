package com.accolite.au.mappers;

import com.accolite.au.dto.TrainingDTO;
import com.accolite.au.models.Training;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingMapper {
    Training toTraining(TrainingDTO trainingDTO);

    TrainingDTO toTrainingDTO(Training training);
}
