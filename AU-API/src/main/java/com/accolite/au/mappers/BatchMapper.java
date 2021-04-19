package com.accolite.au.mappers;

import com.accolite.au.dto.BatchDTO;
import com.accolite.au.models.Batch;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BatchMapper {

    Batch toBatch(BatchDTO batchDTO);

    BatchDTO toBatchDTO(Batch batch);

    List<BatchDTO> toBatchDTOs(List<Batch> batches);
}
