package com.accolite.au.mappers;

import com.accolite.au.dto.StudentDTO;
import com.accolite.au.dto.StudentGroupDTO;
import com.accolite.au.models.Student;
import com.accolite.au.models.StudentGroup;
import com.accolite.au.models.Trainer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentGroupMapper {
//    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Mappings({
            @Mapping(source = "studentGroupDTO.trainerId", target = "trainer.trainerId"),
            @Mapping(source = "studentGroupDTO.batchId", target = "batch.batchId")
    })
    StudentGroup toStudentGroup(StudentGroupDTO studentGroupDTO);

//    @Named("fillStudentsList")
//    private List<StudentDTO> fillStudentsListMethod(List<Student> studentList){
//        return studentMapper.toStudentDTOs(studentList);
//    }

    @Mappings({
            @Mapping(source = "batch.batchId", target = "batchId"),
            @Mapping(source = "trainer.trainerId", target = "trainerId"),
            @Mapping(target = "studentsList", source = "students"),
            @Mapping(target = "students", ignore = true)
    })
    StudentGroupDTO toStudentGroupDTO(StudentGroup group);

    List<StudentGroupDTO> toStudentGroupDTOs(List<StudentGroup> studentGroups);
}

//
//class StudentGroupMapperImpl1 implements StudentGroupMapper{
//
//    @Override
//    public StudentGroup toStudentGroup(StudentGroupDTO studentGroupDTO) {
//        return null;
//    }
//
//    @Override
//    public StudentGroupDTO toStudentGroupDTO(StudentGroup group) {
//        return null;
//    }
//
//    @Override
//    public List<StudentGroupDTO> toStudentGroupDTOs(List<StudentGroup> studentGroups) {
//        List<StudentGroupDTO> studentGroupDTOList = studentMapper.toStudentsList(studentGroups);
//
//        for(StudentGroup studentGroups1 : studentGroups){
//            studentGroups1.
//        }
//    }
//}