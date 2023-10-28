package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.entity.SubjectEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class SubjectDTO {
    private Integer id;
    private String name;

    public static SubjectDTO convertToDto(SubjectEntity entity) {
        if (entity == null) return null;
        SubjectDTO dto = new SubjectDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static SubjectEntity convertToEntity(SubjectDTO dto) {
        if (dto == null) return null;
        SubjectEntity entity = new SubjectEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
