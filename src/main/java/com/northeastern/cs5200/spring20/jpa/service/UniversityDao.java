package com.northeastern.cs5200.spring20.jpa.service;

import com.northeastern.cs5200.spring20.jpa.models.*;

import java.util.List;

public interface UniversityDao  {
    void truncateDatabase();
    Faculty createFaculty(Faculty faculty);
    Student createStudent(Student student);
    Course createCourse(Course course);
    Section createSection(Section section);
    Course addSectionToCourse(Section section, Course course);
    Course setAuthorForCourse(Faculty faculty, Course course);
    Boolean enrollStudentInSection(Student student, Section section);

    List<Person> findAllUsers();
    List<Faculty> findAllFaculty();
    List<Student> findAllStudents();
    List<Course> findAllCourses();
    List<Section> findAllSections();
    List<Course> findCoursesForAuthor(Faculty faculty);
    List<Section> findSectionForCourse(Course course);
    List<Student> findStudentsInSection(Section section);
    List<Section> findSectionsForStudent(Student student);
}

