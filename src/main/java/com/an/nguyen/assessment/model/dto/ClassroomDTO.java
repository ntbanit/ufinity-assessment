package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClassroomDTO {
    private Integer id;
    private String name;
    private Set<StudentDTO> theStudents;
    private Set<ScheduleDTO> theSchedules;

    public static ClassroomDTO convertToDto(ClassroomEntity entity) {
        if (entity == null) return null;
        ClassroomDTO dto = new ClassroomDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public void setStudentInfo(ClassroomEntity entity){
        this.setTheStudents(entity.getStudents().stream().map(StudentDTO::convertToDto)
                .collect(Collectors.toSet()));
    }

    public static ClassroomEntity convertToEntity(ClassroomDTO dto) {
        if (dto == null) return null;
        ClassroomEntity entity = new ClassroomEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
