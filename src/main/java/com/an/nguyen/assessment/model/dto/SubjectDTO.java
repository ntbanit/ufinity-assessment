package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.entity.SubjectEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Getter
@Setter
public class SubjectDTO {
    private Integer id;
    private String name;
    private Set<ScheduleDTO> theSchedules;

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
