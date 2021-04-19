package com.accolite.au.mappers;

import com.accolite.au.dto.ProjectFeedbackDTO;
import com.accolite.au.models.ProjectFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ProjectFeedbackMapper {
    @Mappings({
            @Mapping(source = "projectFeedbackDTO.studentId", target = "student.studentId"),
            @Mapping(source = "projectFeedbackDTO.studentGroupId", target = "studentGroup.studentGroupId")
    })
    ProjectFeedback toProjectFeedback(ProjectFeedbackDTO projectFeedbackDTO);

    @Mappings({
            @Mapping(target = "studentId", source = "projectFeedback.student.studentId"),
            @Mapping(target = "studentGroupId", source = "projectFeedback.studentGroup.studentGroupId")
    })
    ProjectFeedbackDTO toProjectFeedbackDTO(ProjectFeedback projectFeedback);

    List<ProjectFeedbackDTO> toProjectFeedbackDTOsList(List<ProjectFeedback> projectFeedback);

    List<ProjectFeedback> toProjectFeedbackList(List<ProjectFeedbackDTO> projectFeedbackDTOS);

    Set<ProjectFeedbackDTO> toProjectFeedbackDTOsSet(Set<ProjectFeedback> projectFeedback);

    Set<ProjectFeedback> toProjectFeedbackSet(Set<ProjectFeedbackDTO> projectFeedbackDTOS);
}
