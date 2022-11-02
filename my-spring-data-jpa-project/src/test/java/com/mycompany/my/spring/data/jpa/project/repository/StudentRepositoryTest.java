package com.mycompany.my.spring.data.jpa.project.repository;

import com.mycompany.my.spring.data.jpa.project.entity.Guardian;
import com.mycompany.my.spring.data.jpa.project.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public  void  saveStudent(){
        Student student = Student.builder()
                .email("student1@gmail.com")
                .firstName("Student1")
                .lastName("Jones")
                //.guardianName("Guardiano")
                //.guardianEmail("guardiano@gmail.com")
                //.guardianMobile("890912345678")
                .build();
        if (studentRepository.getStudentByEmailAddress(student.getEmail())!=null)
            throw new IllegalStateException("email taken");

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian(){

        Guardian guardian = Guardian.builder()
                .name("Ohrannik")
                .email("ohrannikvasya@mail.ru")
                .mobile("89096667777")
                .build();

        Student student = Student.builder()
                .email("supervovka2007@gmail.com")
                .firstName("Vladimir")
                .lastName("Ivanov")
                .guardian(guardian)
                .build();

        if (studentRepository.getStudentByEmailAddress(student.getEmail())!=null)
            throw new IllegalStateException("email taken");

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents(){
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printAllStudentsWithFirstName(){
        List<Student> studentList =
                studentRepository.findByFirstName("Vladimir");

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printAllStudentsWithFirstNameContaining(){
        List<Student> studentList =
                studentRepository.findByFirstNameContaining("Vlad");

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printAllStudentsBasedOnGuardianName(){
        List<Student> studentList =
                studentRepository.findByGuardianName("Ohrannik");

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printAllStudentsWithLastNameNotNull(){
        List<Student> studentList =
                studentRepository.findByLastNameNotNull();

        System.out.println("studentList = " + studentList);
    }

    @Test
    public void printAllStudentsWithLastNameAndFirstName(){
        Student student =
                studentRepository.findByLastNameAndFirstName("Jones", "Student1");

        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddress(){
        Student student =
                studentRepository.getStudentByEmailAddress("supervovka2007@gmail.com");

        System.out.println("student = " + student);
    }

    @Test
    public void printStudentFirstNameByEmailAddress(){
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress("supervovka2007@gmail.com");

        System.out.println("first name = " + firstName);
    }

    @Test
    public void printStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative("supervovka2007@gmail.com");

        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddressNativeNamedParam(){
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam("supervovka2007@gmail.com");

        System.out.println("student = " + student);
    }

    @Test
    public void printStudentByEmailAddressOrLastNameNativeNamedParam(){
        Student student =
                studentRepository.getStudentByEmailAddressOrLastNameNativeNamedParam("super2007@gmail.com","Jones");

        System.out.println("student = " + student);
    }

    @Test
    public void printUpdateStudentNameByEmail(){
        int number = studentRepository.updateStudentNameByEmail("Petr","supervovka2007@gmail.com");
        System.out.println("number of updated students = " + number);

        printStudentByEmailAddressNative();
    }
}