package com.an.nguyen.assessment.service;

import com.an.nguyen.assessment.model.dto.StudentDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.ScheduleEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import com.an.nguyen.assessment.repository.ClassroomRepository;
import com.an.nguyen.assessment.repository.ScheduleRepository;
import com.an.nguyen.assessment.repository.SubjectRepository;
import com.an.nguyen.assessment.repository.TeacherRepository;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {

        List<TeacherEntity> teacherEntities = teacherRepository.findAll();
        return teacherEntities.stream().map(TeacherDTO::convertToDto).collect(Collectors.toList());
    }

    public TeacherDTO findByEmailAdress(String email){
        TeacherEntity entity = teacherRepository.findByEmailAddress(email);
        return TeacherDTO.convertToDto(entity);
    }

    public TeacherDTO save(TeacherDTO dto){
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


    public List<StudentDTO> showStudentTeaching(Integer teacherId) {

        return null;
    }
}
