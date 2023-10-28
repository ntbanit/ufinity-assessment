package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ClassroomDTO {
    private Integer id;
    private String name;

    public static ClassroomDTO convertToDto(ClassroomEntity entity) {
        if (entity == null) return null;
        ClassroomDTO dto = new ClassroomDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static ClassroomEntity convertToEntity(ClassroomDTO dto) {
        if (dto == null) return null;
        ClassroomEntity entity = new ClassroomEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
