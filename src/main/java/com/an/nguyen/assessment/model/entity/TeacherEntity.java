package com.an.nguyen.assessment.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String emailAddress;
    private String name;
    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
    private Set<LectureEntity> schedules;
}
