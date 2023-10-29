package com.an.nguyen.assessment.repository;

import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {

    @Query(value = "SELECT DISTINCT t FROM " +
            "LectureEntity sch JOIN sch.teacher t " +
            "WHERE sch.subject.id = :subjectId")
    public List<TeacherEntity> getTeachersTaught(Integer subjectId);

    @Query(value = "SELECT DISTINCT c FROM " +
            "LectureEntity sch JOIN sch.classroom c " +
            "WHERE sch.subject.id = :subjectId")
    public List<ClassroomEntity> getClassroomsLearnt(Integer subjectId);
}
