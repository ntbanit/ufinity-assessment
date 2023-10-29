package com.an.nguyen.assessment.service;

import com.an.nguyen.assessment.model.dto.ClassroomDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import com.an.nguyen.assessment.model.response.TeacherReportResponse;
import com.an.nguyen.assessment.repository.TeacherRepository;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {

        List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        return teacherEntities.stream().map(TeacherDTO::convertToDto).collect(Collectors.toList());
    }

    public TeacherDTO findByEmailAdress(String email) {
        TeacherEntity entity = teacherRepository.findByEmailAddress(email);
        return TeacherDTO.convertToDto(entity);
    }

    public TeacherDTO save(TeacherDTO dto) {
        TeacherEntity entity = teacherRepository.save(TeacherDTO.convertToEntity(dto));
        dto.setId(entity.getId());
        return dto;
    }

    public TeacherDTO findById(Integer id) {
        TeacherEntity entity = teacherRepository.findById(id).get();
        return TeacherDTO.convertToDto(entity);
    }

    public void delete(Integer id) {
        teacherRepository.deleteById(id);
    }


    public TeacherReportResponse report(Integer teacherId) throws BusinessFlowException {
        TeacherDTO teacher = this.findById(teacherId);
        if (teacher == null) {
            throw new BusinessFlowException("Teacher not found");
        }
        TeacherReportResponse response = new TeacherReportResponse();
        response.setTeacher(teacher);
        List<Object[]> list = teacherRepository.getClassroomsAndSubjects(teacherId);
        Map<ClassroomDTO, Set<SubjectDTO>> scheduleMap = new HashMap<>();
        for (Object objects[] : list) {
            ClassroomEntity classroomEntity = (ClassroomEntity) objects[0];
            ClassroomDTO classroomDTO = ClassroomDTO.convertToDto(classroomEntity);
            classroomDTO.setStudentInfo(classroomEntity);
            Set<SubjectDTO> subjectSet;
            if (scheduleMap.containsKey(classroomDTO)) {
                subjectSet = scheduleMap.get(classroomDTO);
            } else {
                subjectSet = new HashSet<>();
            }
            subjectSet.add(SubjectDTO.convertToDto((SubjectEntity) objects[1]));
        }
        for (ClassroomDTO key : scheduleMap.keySet()) {
            TeacherReportResponse.SubjectsInClass object = response.new SubjectsInClass(key, scheduleMap.get(key));
            response.getSchedule().add(object);
        }
        return response;
    }
}
