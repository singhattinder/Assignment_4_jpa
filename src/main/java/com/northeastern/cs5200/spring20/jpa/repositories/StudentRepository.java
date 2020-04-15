package com.northeastern.cs5200.spring20.jpa.repositories;

import com.northeastern.cs5200.spring20.jpa.models.Course;
import com.northeastern.cs5200.spring20.jpa.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("SELECT s FROM  Student s, Enrollment en, Section sec " +
            "WHERE en.section.id = sec.id and en.student.id = s.id and sec.id=:sectionId  ")
    List<Student> findStudentsInSection(
            @Param("sectionId") Integer sectionId);
}
