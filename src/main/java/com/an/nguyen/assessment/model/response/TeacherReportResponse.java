package com.an.nguyen.assessment.model.response;

import com.an.nguyen.assessment.model.dto.ClassroomDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TeacherReportResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class SubjectsInClass {
        ClassroomDTO classroom;
        Set<SubjectDTO> subjects;
    }

    private TeacherDTO teacher;
    private Set<SubjectsInClass> schedule;

    public TeacherReportResponse() {
        schedule = new HashSet<>();
    }
}
