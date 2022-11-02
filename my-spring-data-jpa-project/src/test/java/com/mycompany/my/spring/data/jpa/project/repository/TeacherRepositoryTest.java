package com.mycompany.my.spring.data.jpa.project.repository;

import com.mycompany.my.spring.data.jpa.project.entity.Course;
import com.mycompany.my.spring.data.jpa.project.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository repository;

    @Test
    public void saveTeacher(){

        Course course1 =
                Course.builder()
                        .title("Unity Pro")
                        .credit(5)
                        .build();

        Course course2 =
                Course.builder()
                        .title("Unreal Engine Pro")
                        .credit(5)
                        .build();

        List<Course> courses = List.of(course1,course2);

        Teacher teacher =
                Teacher.builder()
                        .firstName("Mark")
                        .lastName("Felix")
                        //.courses(courses)
                        .build();

        repository.save(teacher);
    }


}