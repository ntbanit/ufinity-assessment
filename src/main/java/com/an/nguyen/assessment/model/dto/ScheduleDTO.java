package com.an.nguyen.assessment.model.dto;

import com.an.nguyen.assessment.model.entity.LectureEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ScheduleDTO {
    private Integer id;
    private TeacherDTO theTeacher;
    private ClassroomDTO theClassroom;
    private SubjectDTO theSubject;

    public static ScheduleDTO convertToDto(LectureEntity entity) {
        if (entity == null) return null;
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(entity, dto);
        BeanUtils.copyProperties(entity.getSubject(), dto.getTheSubject());
        BeanUtils.copyProperties(entity.getTeacher(), dto.getTheTeacher());
        BeanUtils.copyProperties(entity.getClassroom(), dto.getTheClassroom());
        return dto;
    }
}
