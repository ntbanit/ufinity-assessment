package com.an.nguyen.assessment.utils;

import com.an.nguyen.assessment.model.StudentType;
import com.an.nguyen.assessment.model.entity.ClassroomEntity;
import com.an.nguyen.assessment.model.entity.StudentEntity;
import com.an.nguyen.assessment.model.entity.SubjectEntity;
import com.an.nguyen.assessment.model.entity.TeacherEntity;
import com.an.nguyen.assessment.repository.AccountRepository;
import com.an.nguyen.assessment.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Component
public class DemoData {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClassroomRepository classroomRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        String classNames[] = {"1A", "1B", "2A", "3B", "4C"};
        List<ClassroomEntity> classrooms = new ArrayList<>();
        for (String className : classNames) {
            ClassroomEntity classroom = new ClassroomEntity();
            classroom.setName(className);
            classroom.setStudents(new HashSet<>());
            classroom.setSchedules(new HashSet<>());
            classrooms.add(classroom);
        }

        String studentMails[] = {"test_data@gmail.com", "first_student@yahoo.com", "second_student@hotmail.com",
                "another_one@vti.com.vn", "my.fake.account@gits.com.vn", "dont.believe.me@spring.io",
                "new_student@vnexpress.com", "test_random@gmail.com", "fake_student@yahoo.com"};
        String studentNames[] = {"Jim", "Teddy", "An", "Nguyen", "Chen", "Micheal", "Joseph", "Tanaka", "Lee"};
        Random random = new Random();
        for (int i = 0; i < studentMails.length; i++) {
            StudentEntity student = new StudentEntity();
            student.setName(studentNames[i]);
            student.setEmailAddress(studentMails[i]);
            student.setType(StudentType.INTERNAL);
            int index = random.nextInt(classNames.length);
            classrooms.get(index).getStudents().add(student);
        }

        String teacherMails[] = {"teacher_one@gmail.com", "fake.teacher@gov.vn", "trainer@yahoo.com"};
        String teacherNames[] = {"Richard", "Stephen", "Drew"};
        List<TeacherEntity> teachers = new ArrayList<>();
        for (int i = 0; i < teacherMails.length; i++) {
            TeacherEntity teacher = new TeacherEntity();
            teacher.setEmailAddress(teacherMails[i]);
            teacher.setName(teacherNames[i]);
            teachers.add(teacher);
        }

        String subjectNames[] = {"Math", "Java", "SQL", "HTML", "CSS", "JS", "P.E"};
        List<SubjectEntity> subjects = new ArrayList<>();
        for (int i = 0; i < subjectNames.length; i++) {
            SubjectEntity subject = new SubjectEntity();
            subject.setName(studentNames[i]);
            subjects.add(subject);
        }

        classroomRepository.saveAll(classrooms);
    }
}