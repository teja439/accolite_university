package com.accolite.au.mappers;

import com.accolite.au.dto.EduthrillTestDTO;
import com.accolite.au.models.EduthrillTest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EduthrillTestMapper {

    EduthrillTest toEduthrillTest(EduthrillTestDTO eduthrillTestDTO);

    EduthrillTestDTO toEduthrillTestDTO(EduthrillTest eduthrillTest);

    List<EduthrillTestDTO> toEduthrillTestDTOs(List<EduthrillTest> eduthrillTests);

}
