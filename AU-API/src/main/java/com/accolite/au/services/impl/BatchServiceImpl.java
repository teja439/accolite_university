package com.accolite.au.services.impl;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.dto.CustomEntityNotFoundExceptionDTO;
import com.accolite.au.dto.SuccessResponseDTO;
import com.accolite.au.mappers.BatchMapper;
import com.accolite.au.models.Batch;
import com.accolite.au.repositories.BatchRepository;
import com.accolite.au.services.BatchService;
import com.accolite.au.utils.ObjectMergerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final BatchMapper batchMapper;
    private final ObjectMergerUtil objectMergerUtil;

    public BatchServiceImpl(BatchRepository batchRepository, BatchMapper batchMapper, ObjectMergerUtil objectMergerUtil) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
        this.objectMergerUtil = objectMergerUtil;
    }

    @Override
    public BatchDTO addBatch(BatchDTO batchDTO) {
        Batch batch = batchMapper.toBatch(batchDTO);
        return batchMapper.toBatchDTO(batchRepository.saveAndFlush(batch));
    }

    @Override
    public List<BatchDTO> getAllBatches(){
        List<Batch> batches = batchRepository.findAllByOrderByBatchName();
        return batchMapper.toBatchDTOs(batches);
    }

    @Override
    public BatchDTO getBatch(int batchId){
        if(batchRepository.existsById(batchId) == false){
            throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
        }
        return batchMapper.toBatchDTO(batchRepository.getOne(batchId));
    }

    @Override
    public SuccessResponseDTO deleteBatch(int batchId){
        if(batchRepository.existsById(batchId)){
            batchRepository.deleteById(batchId);
            System.out.println("Done");
            return new SuccessResponseDTO("Batch with id : " + batchId + " deleted Successfully", HttpStatus.OK);
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchId + " not Found");
    }

    @Override
    public BatchDTO updateBatch(BatchDTO batchDTO) throws IllegalAccessException {
        if(batchRepository.existsById(batchDTO.getBatchId())) {
            BatchDTO updatedBatch = objectMergerUtil.merger(batchDTO, batchMapper.toBatchDTO(batchRepository.getOne(batchDTO.getBatchId())));
            return batchMapper.toBatchDTO(batchRepository.saveAndFlush(batchMapper.toBatch(updatedBatch)));
        }
        throw new CustomEntityNotFoundExceptionDTO("Batch with id : " + batchDTO.getBatchId() + " not Found");
    }
}
