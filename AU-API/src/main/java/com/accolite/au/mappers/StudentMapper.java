package com.accolite.au.mappers;

import com.accolite.au.dto.StudentDTO;
import com.accolite.au.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mappings({@Mapping(target = "batch", ignore = true), @Mapping(source = "studentDTO.batchId", target = "batch.batchId")})
    Student toStudent(StudentDTO studentDTO);

    @Mappings({@Mapping(target = "batch", ignore = true), @Mapping(source = "studentDTO.batchId", target = "batch.batchId")})
    Set<Student> toStudentsList(Set<StudentDTO> studentDTOList);

    @Mappings({@Mapping(source = "batch.batchId", target = "batchId")})
    StudentDTO toStudentDTO(Student student);

    List<StudentDTO> toStudentDTOs(List<Student> students);
}
