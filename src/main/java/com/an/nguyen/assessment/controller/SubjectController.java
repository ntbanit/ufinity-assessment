package com.an.nguyen.assessment.controller;

import com.an.nguyen.assessment.model.dto.ClassroomDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.response.StudentReportResponse;
import com.an.nguyen.assessment.service.SubjectService;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDTO> getAll() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectDTO get(@PathVariable Integer id) {
        return subjectService.findById(id);
    }

    @PostMapping("/save")
    public SubjectDTO save(@RequestBody SubjectDTO subjectDTO) {
        return subjectService.save(subjectDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        subjectService.delete(id);
    }

    @GetMapping("/report/{subject_id}")
    public Object report(@PathVariable Integer subjectId) throws BusinessFlowException {
        return null;
    }
}
