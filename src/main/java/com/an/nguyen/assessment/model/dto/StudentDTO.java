package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.StudentType;
import com.an.nguyen.assessment.model.entity.StudentEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String emailAddress;
    private String name;
    private StudentType type;
    private ClassroomDTO theClassroom;

    public static StudentDTO convertToDto(StudentEntity entity) {
        if (entity == null) return null;
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void setClassroomInfo(StudentEntity entity){
        this.setTheClassroom(ClassroomDTO.convertToDto(entity.getClassroom()));
    }

    public static StudentEntity convertToEntity(StudentDTO dto) {
        if (dto == null) return null;
        StudentEntity entity = new StudentEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
