package com.an.nguyen.assessment.service;

import com.an.nguyen.assessment.model.dto.ClassroomDTO;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.LectureEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import com.an.nguyen.assessment.repository.ClassroomRepository;
import com.an.nguyen.assessment.repository.SubjectRepository;
import com.an.nguyen.assessment.repository.TeacherRepository;
import com.an.nguyen.assessment.utils.BusinessFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomRepository classroomRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<ClassroomDTO> getAllClassroom(){
        List<ClassroomEntity> list = classroomRepository.findAll();
        return list.stream().map(ClassroomDTO::convertToDto).collect(Collectors.toList());
    }

    public ClassroomDTO save(ClassroomDTO dto){
        ClassroomEntity entity = classroomRepository.save(ClassroomDTO.convertToEntity(dto));
        dto.setId(entity.getId());
        return dto;
    }

    public ClassroomDTO findById(Integer id) {
        ClassroomEntity entity = classroomRepository.findById(id).get();
        return ClassroomDTO.convertToDto(entity);
    }

    public void delete(Integer id) {
        classroomRepository.deleteById(id);
    }

    public void scheduleTeacherForSubject(Integer classroomId, Integer teacherId, Integer subjectId) throws BusinessFlowException {
        ClassroomEntity classroom = classroomRepository.findById(classroomId).get();
        if(classroom == null){
            throw new BusinessFlowException("Classroom not found");
        }
        TeacherEntity teacher = teacherRepository.findById(teacherId).get();
        if(teacher == null){
            throw new BusinessFlowException("Teacher not found");
        }
        SubjectEntity subject = subjectRepository.findById(subjectId).get();
        if(subject == null){
            throw new BusinessFlowException("Subject not found");
        }
        Set<LectureEntity> theSchedules = classroom.getSchedules();
        LectureEntity schedule = new LectureEntity();
        schedule.setTeacher(teacher);
        schedule.setSubject(subject);
        theSchedules.add(schedule);
        classroomRepository.save(classroom);
    }
}
