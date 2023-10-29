package com.an.nguyen.assessment.model.response;

import com.an.nguyen.assessment.model.dto.StudentDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectReportResponse {
    private SubjectDTO subject;
    private List<StudentDTO> students;
    private List<TeacherDTO> teachers;
}
