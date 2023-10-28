package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
}
