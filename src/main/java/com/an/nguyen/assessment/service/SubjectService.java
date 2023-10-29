package com.an.nguyen.assessment.service;

import com.an.nguyen.assessment.model.dto.StudentDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.StudentEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import com.an.nguyen.assessment.model.response.SubjectReportResponse;
import com.an.nguyen.assessment.repository.SubjectRepository;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDTO> getAllSubjects() {
        List<SubjectEntity> list = subjectRepository.findAll();
        return list.stream().map(SubjectDTO::convertToDto).collect(Collectors.toList());
    }

    public SubjectDTO save(SubjectDTO dto) {
        SubjectEntity entity = subjectRepository.save(SubjectDTO.convertToEntity(dto));
        dto.setId(entity.getId());
        return dto;
    }

    public void delete(Integer id) {
        subjectRepository.deleteById(id);
    }

    public SubjectDTO findById(Integer id) {
        SubjectEntity entity = subjectRepository.findById(id).get();
        return SubjectDTO.convertToDto(entity);
    }

    public SubjectReportResponse report(Integer subjectId) throws BusinessFlowException {
        SubjectEntity entity = subjectRepository.findById(subjectId).get();
        if (entity == null) {
            throw new BusinessFlowException("Subject not found");
        }
        SubjectReportResponse response = new SubjectReportResponse();
        response.setSubject(SubjectDTO.convertToDto(entity));
        List<ClassroomEntity> classroomList = subjectRepository.getClassroomsLearnt(subjectId);
        List<StudentDTO> studentList = new ArrayList<>();
        for (ClassroomEntity classroom : classroomList) {
            Set<StudentEntity> list = classroom.getStudents();
            for (StudentEntity studentEntity : list) {
                StudentDTO dto = StudentDTO.convertToDto(studentEntity);
                dto.setClassroomInfo(studentEntity);
                studentList.add(dto);
            }
        }
        response.setStudents(studentList);
        List<TeacherEntity> teacherList = subjectRepository.getTeachersTaught(subjectId);
        response.setTeachers(teacherList.stream().map(TeacherDTO::convertToDto)
                .collect(Collectors.toList()));
        return response;
    }
}
