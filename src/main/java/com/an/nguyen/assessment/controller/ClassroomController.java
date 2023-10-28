package com.an.nguyen.assessment.controller;

import com.an.nguyen.assessment.model.dto.ClassroomDTO;
import com.an.nguyen.assessment.service.ClassroomService;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @GetMapping
    public List<ClassroomDTO> getAll() {
        return classroomService.getAllClassroom();
    }

    @GetMapping("/{id}")
    public ClassroomDTO get(@PathVariable Integer id) {
        return classroomService.findById(id);
    }

    @PostMapping("/save")
    public ClassroomDTO save(@RequestBody ClassroomDTO classroomDTO) {
        return classroomService.save(classroomDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        classroomService.delete(id);
    }

    @GetMapping("/schedule/{classroom_id}/{teacher_id}/{subject_id}/")
    public void schedule(@PathVariable Integer classroomId, @PathVariable Integer teacherId, @PathVariable Integer subjectId) throws BusinessFlowException {
        classroomService.scheduleTeacherForSubject(classroomId, teacherId, subjectId);
    }
}
