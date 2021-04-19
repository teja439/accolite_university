package com.accolite.au.mappers;

import com.accolite.au.dto.EduthrillSessionDTO;
import com.accolite.au.models.EduthrillSession;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface EduthrillSessionMapper {
    @Mappings({
            @Mapping(source = "student.studentId", target = "studentId"),
            @Mapping(source = "eduthrillTest.eduthrillTestId", target = "eduthrillTestId")
    })
    EduthrillSessionDTO toEduthrillSessionDTO(EduthrillSession eduthrillSession);

    @Mappings({
            @Mapping(source = "studentId", target = "student.studentId"),
            @Mapping(source = "eduthrillTestId", target = "eduthrillTest.eduthrillTestId")
    })
    EduthrillSession toEduthrillSession(EduthrillSessionDTO eduthrillSessionDTO);

    Set<EduthrillSessionDTO> toEduthrillSessionDTOs(Set<EduthrillSession> eduthrillSessions);
    List<EduthrillSessionDTO> toEduthrillSessionDTOsList(Set<EduthrillSession> eduthrillSessions);
}
