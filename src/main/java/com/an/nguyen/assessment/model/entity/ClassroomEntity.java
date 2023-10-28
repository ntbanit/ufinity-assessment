package com.an.nguyen.assessment.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "classroom")
@Getter
@Setter
public class ClassroomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private Set<StudentEntity> students;
    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY)
    private Set<ScheduleEntity> schedules;
}
