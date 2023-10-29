package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {
    TeacherEntity findByEmailAddress(String email);

    @Query(value = "SELECT c, sub FROM LectureEntity sch " +
            "JOIN sch.classroom c " +
            "JOIN sch.subject sub " +
            "WHERE sch.teacher.id = :teacherId ORDER BY sch.teacher.id, sch.classroom.id, sch.subject.id")
    public List<Object[]> getClassroomsAndSubjects(Integer teacherId);


}
