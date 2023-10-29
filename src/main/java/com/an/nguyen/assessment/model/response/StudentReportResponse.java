package com.an.nguyen.assessment.model.response;

import com.an.nguyen.assessment.model.dto.StudentDTO;
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
public class StudentReportResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class SubjectsByTeacher {
        TeacherDTO teacher;
        Set<SubjectDTO> subjects;
    }

    private StudentDTO student;
    private Set<SubjectsByTeacher> schedule;

    public StudentReportResponse() {
        schedule = new HashSet<>();
    }
}
