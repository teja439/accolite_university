package com.accolite.au.services;

import com.accolite.au.dto.TrainingDTO;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface TrainingService {

    TrainingDTO markAndUpdateTrainingData(TrainingDTO trainingDTO, char type);

    TrainingDTO getTrainingData(int sessionId, int studentId);

	ObjectNode getAllTrainingData(char type, int batchId);
}
