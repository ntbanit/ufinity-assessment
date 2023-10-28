package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.entity.TeacherEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class TeacherDTO {
    private Integer id;
    private String emailAddress;
    private String name;

    public static TeacherDTO convertToDto(TeacherEntity entity) {
        if (entity == null) return null;
        TeacherDTO dto = new TeacherDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static TeacherEntity convertToEntity(TeacherDTO dto) {
        if (dto == null) return null;
        TeacherEntity entity = new TeacherEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
