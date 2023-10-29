package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    StudentEntity findByEmailAddress(String email);

    @Query(value = "SELECT DISTINCT t, sub FROM " +
            "LectureEntity sch JOIN sch.teacher t " +
            "JOIN sch.classroom c " +
            "JOIN sch.subject sub " +
            "JOIN StudentEntity s ON c.id = s.classroom.id " +
            "WHERE s.id = :studentId ORDER BY sch.teacher.id, sch.subject.id")
    public List<Object[]> getTeachersAndSubjects(Integer studentId);
}
