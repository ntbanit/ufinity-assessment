package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
}
