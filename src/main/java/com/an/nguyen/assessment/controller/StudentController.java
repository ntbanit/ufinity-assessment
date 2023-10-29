package com.an.nguyen.assessment.controller;

import com.an.nguyen.assessment.model.dto.StudentDTO;
import com.an.nguyen.assessment.model.response.StudentReportResponse;
import com.an.nguyen.assessment.service.StudentService;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO get(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @PostMapping("/save")
    public StudentDTO save(@RequestBody StudentDTO student) {
        return studentService.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }

    @GetMapping("/join/{student_id}/{classroom_id}")
    public StudentDTO get(@PathVariable Integer studentId, @PathVariable Integer classroomId) throws BusinessFlowException {
        return studentService.joinClassroom(studentId, classroomId);
    }
    @GetMapping("/report/{student_id}")
    public StudentReportResponse report(@PathVariable Integer studentId) throws BusinessFlowException {
        return studentService.getTeachersAndSubjects(studentId);
    }
}