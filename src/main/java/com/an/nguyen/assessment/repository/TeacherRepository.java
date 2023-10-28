package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository  extends JpaRepository<TeacherEntity, Integer> {
    TeacherEntity findByEmailAddress(String email);
}
