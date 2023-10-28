package com.an.nguyen.assessment.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectEntity subject;
}
