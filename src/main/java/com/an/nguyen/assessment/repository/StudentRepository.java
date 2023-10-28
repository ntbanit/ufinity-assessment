package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    StudentEntity findByEmailAddress(String email);
}
