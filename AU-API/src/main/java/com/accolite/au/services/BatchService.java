package com.accolite.au.services;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.SuccessResponseDTO;

import java.util.List;

public interface BatchService {
    BatchDTO addBatch(BatchDTO batch);

    List<BatchDTO> getAllBatches();

    BatchDTO getBatch(int batchId);

    SuccessResponseDTO deleteBatch(int batchId);

    BatchDTO updateBatch(BatchDTO batchDTO) throws IllegalAccessException;

}
