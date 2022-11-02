package com.mycompany.my.spring.data.jpa.project.repository;

import com.mycompany.my.spring.data.jpa.project.entity.Course;
import com.mycompany.my.spring.data.jpa.project.entity.CourseMaterial;
import com.mycompany.my.spring.data.jpa.project.entity.Student;
import com.mycompany.my.spring.data.jpa.project.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository repository;

    @Test
    public void printCourses(){
        List<Course> courses =
                repository.findAll();
        System.out.println("courses = " + courses);
    }

//    @Test
//    public void updateCourseByTitle(){
//        repository.updateCourseByTitle("Spring Boot from Zero to Hero",1L);
//    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher =
                Teacher.builder()
                        .firstName("Brian")
                        .lastName("Shaw")
                        .build();

//        CourseMaterial courseMaterial =
//                CourseMaterial.builder()
//                        .url("www.dummy.com")
//                        .build();

        Course course =
                Course.builder()
                        .title(".net")
                        .credit(8)
                        .teacher(teacher)
                        //.courseMaterial(courseMaterial)
                        .build();

        repository.save(course);
    }

    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);
        List<Course> courses =
                repository.findAll(secondPageWithTwoRecords)
                        .getContent();

        long totalElements =
                repository.findAll(secondPageWithTwoRecords)
                                .getTotalElements();

        long totalPages =
                repository.findAll(secondPageWithTwoRecords)
                                .getTotalPages();

        System.out.println("totalPages = " + totalPages);

        System.out.println("totalElements = " + totalElements);

        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );
        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending()
                );

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                                .descending()
                                .and(Sort.by("credit"))
                );

        List<Course> courses1 =
            repository.findAll(sortByTitle).getContent();

        List<Course> courses2 =
                repository.findAll(sortByCreditDesc).getContent();

        List<Course> courses3=
                repository.findAll(sortByTitleAndCreditDesc).getContent();

        System.out.println("courses1 = " + courses1);
        System.out.println("courses2 = " + courses2);
        System.out.println("courses3 = " + courses3);
    }

    @Test
    public void findByTitleContaining(){

        Pageable firstPageTenRecords =
                PageRequest.of(0,2);


        List<Course> courses =
                repository.findByTitleContaining("U",  firstPageTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void  saveCourseWithStudentAndTeacher(){

        Teacher teacher = Teacher
                .builder()
                .firstName("Semen")
                .lastName("Herrington")
                .build();

        Student student = Student
                .builder()
                .firstName("Jabrony")
                .lastName("Williams")
                .email("jabrony@gmail.com")
                .build();

        Course course = Course
                .builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        repository.save(course);
    }
}