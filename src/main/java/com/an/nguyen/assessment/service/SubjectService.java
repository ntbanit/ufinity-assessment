package com.an.nguyen.assessment.service;

import com.an.nguyen.assessment.model.dto.ClassroomDTO;
import com.an.nguyen.assessment.model.dto.SubjectDTO;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDTO> getAllSubjects(){
        List<SubjectEntity> list = subjectRepository.findAll();
        return list.stream().map(SubjectDTO::convertToDto).collect(Collectors.toList());
    }

    public SubjectDTO save(SubjectDTO dto){
        SubjectEntity entity = subjectRepository.save(SubjectDTO.convertToEntity(dto));
        dto.setId(entity.getId());
        return dto;
    }

    public void delete(Integer id){
        subjectRepository.deleteById(id);
    }

    public SubjectDTO findById(Integer id) {
        SubjectEntity entity = subjectRepository.findById(id).get();
        return SubjectDTO.convertToDto(entity);
    }
}
