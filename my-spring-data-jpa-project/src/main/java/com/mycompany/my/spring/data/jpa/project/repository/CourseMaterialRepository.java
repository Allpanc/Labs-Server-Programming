package com.mycompany.my.spring.data.jpa.project.repository;


import com.mycompany.my.spring.data.jpa.project.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial, Long> {
}
