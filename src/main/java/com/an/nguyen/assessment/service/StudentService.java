package com.an.nguyen.assessment.service;

import com.an.nguyen.assessment.model.dto.StudentDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.dto.TeacherDTO;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.StudentEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import com.an.nguyen.assessment.model.response.StudentReportResponse;
import com.an.nguyen.assessment.repository.ClassroomRepository;
import com.an.nguyen.assessment.repository.StudentRepository;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream().map(StudentDTO::convertToDto).collect(Collectors.toList());
    }

    public StudentDTO save(StudentDTO studentDto) {
        StudentEntity entity = studentRepository.save(StudentDTO.convertToEntity(studentDto));
        studentDto.setId(entity.getId());
        return studentDto;
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    public StudentDTO findById(Integer id) {
        Optional<StudentEntity> otp = studentRepository.findById(id);
        if(!otp.isPresent()) return null;
        return StudentDTO.convertToDto(otp.get());
    }

    public StudentDTO findByEmailAdress(String email) {
        StudentEntity studentEntity = studentRepository.findByEmailAddress(email);
        return StudentDTO.convertToDto(studentEntity);
    }

    public StudentDTO joinClassroom(Integer studentId, Integer classroomId) throws BusinessFlowException {
        StudentEntity student = studentRepository.findById(studentId).get();
        if (student == null) {
            throw new BusinessFlowException("Student not found");
        }
        ClassroomEntity classroom = classroomRepository.findById(classroomId).get();
        if (student == null) {
            throw new BusinessFlowException("classroom not found");
        }
        Set<StudentEntity> studentList = classroom.getStudents();
        if (studentList.size() == 500) {
            throw new BusinessFlowException("classroom is full. please choose another classroom to join");
        }
        studentList.add(student);
        classroomRepository.save(classroom);

        student.setClassroom(classroom);
        return StudentDTO.convertToDto(student);
    }

    public StudentReportResponse getTeachersAndSubjects(Integer studentId) throws BusinessFlowException {
        StudentEntity student = studentRepository.findById(studentId).get();
        if (student == null) {
            throw new BusinessFlowException("Student not found");
        }
        StudentDTO dto = StudentDTO.convertToDto(student);
        dto.setClassroomInfo(student);

        StudentReportResponse response = new StudentReportResponse();
        response.setStudent(dto);

        List<Object[]> list = studentRepository.getTeachersAndSubjects(studentId);
        Map<TeacherDTO, Set<SubjectDTO>> scheduleMap = new HashMap<>();
        for (Object[] objects : list) {
            TeacherDTO teacher = TeacherDTO.convertToDto((TeacherEntity) objects[0]);
            Set<SubjectDTO> subjectSet;
            if (scheduleMap.containsKey(teacher)) {
                subjectSet = scheduleMap.get(teacher);
            } else {
                subjectSet = new HashSet<>();
            }
            subjectSet.add(SubjectDTO.convertToDto((SubjectEntity) objects[1]));
        }
        for (TeacherDTO key : scheduleMap.keySet()) {
            StudentReportResponse.SubjectsByTeacher object = response.new SubjectsByTeacher(key, scheduleMap.get(key));
            response.getSchedule().add(object);
        }
        return response;
    }
}
