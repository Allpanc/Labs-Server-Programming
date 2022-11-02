package com.mycompany.my.spring.data.jpa.project.repository;

import com.mycompany.my.spring.data.jpa.project.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    List<Student> findByGuardianName(String guardianName);

    Student findByLastNameAndFirstName(String lastName,
                                       String firstName);

    // С помощью запроса на языке запросов jpql
    // Позиционные параметры - ?1 означает первый параметр
    @Query("select s from Student s where s.email = ?1")
    Student getStudentByEmailAddress(String email);

    @Query("select s.firstName from Student s where s.email = ?1")
    String getStudentFirstNameByEmailAddress(String email);

    // Нативный запрос - запрос на обычном SQL, "родной" для БД
    @Query(
            //value = "SELECT * FROM tbl_student where email_address = ?1",
            value = "SELECT * FROM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String email);

    // Именованные параметры
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = :email",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("email") String email
    );

    // Именованные параметры
    @Query(
            value = "SELECT * FROM tbl_student s where s.last_name = :last_name " +
                    "or s.email_address = :email",
            nativeQuery = true
    )
    Student getStudentByEmailAddressOrLastNameNativeNamedParam(
            @Param("email") String email,
            @Param("last_name") String lastName
    );

    // @Modifying добавляется к @Query, чтобы добавить возможность
    // вставки, обновления и удаления, а не только выборки
    @Modifying
    // @Transactional добавляется для использования транзакций
    // при вызове данного метода
    // Обычно лучше эту аннотацию применять в Service слое
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    // Функция возвращает количество обновленных сущностей
    int updateStudentNameByEmail(String firstName, String email);
}
