package com.northeastern.cs5200.spring20.jpa.service;

import com.northeastern.cs5200.spring20.jpa.models.*;
import com.northeastern.cs5200.spring20.jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityImpl implements UniversityDao {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private StudentRepository studentRepository;



    @Override
    public void truncateDatabase() {
        courseRepository.deleteAll();
        enrollmentRepository.deleteAll();
        facultyRepository.deleteAll();
        personRepository.deleteAll();
        sectionRepository.deleteAll();
        studentRepository.deleteAll();
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Section createSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public Course addSectionToCourse(Section section, Course course) {
        section.setCourse(course);
        sectionRepository.save(section);
        course.addSection(section);
        return courseRepository.save(course);
    }

    @Override
    public Course setAuthorForCourse(Faculty faculty, Course course) {

        faculty.addCourse(course);
        facultyRepository.save(faculty);

        course.setFaculty(faculty);
        return courseRepository.save(course);
    }

    @Override
    public Boolean enrollStudentInSection(Student student, Section section) {

        if (section.getSeats() > 0) {
            Enrollment enrollment = new Enrollment(section, student);
            student.addEnrollment(enrollment);
            section.addEnrollment(enrollment);
            enrollmentRepository.save(enrollment);
            section.setSeats(section.getSeats() - 1);
            sectionRepository.save(section);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> findAllUsers() {
        return (List<Person>)personRepository.findAll();
    }

    @Override
    public List<Faculty> findAllFaculty() {
        return (List<Faculty>)facultyRepository.findAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return (List<Student>)studentRepository.findAll();
    }

    @Override
    public List<Course> findAllCourses() {
        return (List<Course>)courseRepository.findAll();
    }

    @Override
    public List<Section> findAllSections() {
        return (List<Section>)sectionRepository.findAll();
    }

    @Override
    public List<Course> findCoursesForAuthor(Faculty faculty) {
        return courseRepository.findCourseForFaculty(faculty.getId());
    }

    @Override
    public List<Section> findSectionForCourse(Course course) {
        return sectionRepository.findSectionForCourse(course.getId());
    }

    @Override
    public List<Student> findStudentsInSection(Section section) {
        return studentRepository.findStudentsInSection(section.getId());
    }

    @Override
    public List<Section> findSectionsForStudent(Student student) {
        return sectionRepository.findSectionsForStudent(student.getId());
    }
}
