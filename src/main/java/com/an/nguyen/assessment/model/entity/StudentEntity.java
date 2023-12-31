package com.an.nguyen.assessment.model.entity;

import com.an.nguyen.assessment.model.StudentType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String emailAddress;
    private String name;
    private StudentType type;
    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassroomEntity classroom;
}
