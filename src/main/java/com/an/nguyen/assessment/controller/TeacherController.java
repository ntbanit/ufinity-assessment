package com.an.nguyen.assessment.controller;

import com.an.nguyen.assessment.model.dto.StudentDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import com.an.nguyen.assessment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<TeacherDTO> getAll() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDTO get(@PathVariable Integer id) {
        return teacherService.findById(id);
    }

    @PostMapping("/save")
    public TeacherDTO save(@RequestBody TeacherDTO teacher) {
        return teacherService.save(teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        teacherService.delete(id);
    }

    @GetMapping("/showStudent/{id}")
    public List<StudentDTO> showStudentTeaching(@PathVariable Integer teacherId){
        return teacherService.showStudentTeaching(teacherId);
    }

}