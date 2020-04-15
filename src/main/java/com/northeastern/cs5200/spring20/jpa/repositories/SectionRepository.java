package com.northeastern.cs5200.spring20.jpa.repositories;

import com.northeastern.cs5200.spring20.jpa.models.Course;
import com.northeastern.cs5200.spring20.jpa.models.Section;
import com.northeastern.cs5200.spring20.jpa.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section, Integer> {

    @Query("SELECT u FROM  Section u WHERE u.course.id =:courseId")
    List<Section> findSectionForCourse(
            @Param("courseId") Integer courseId);

    @Query("SELECT sec FROM  Student s, Enrollment en, Section sec " +
            "WHERE en.section.id = sec.id and en.student.id = s.id and s.id=:studentId  ")
    List<Section> findSectionsForStudent(
            @Param("studentId") Integer studentId);
}
