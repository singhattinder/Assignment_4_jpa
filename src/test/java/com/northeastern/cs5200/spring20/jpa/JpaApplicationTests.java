package com.northeastern.cs5200.spring20.jpa;

import com.northeastern.cs5200.spring20.jpa.models.*;

import com.northeastern.cs5200.spring20.jpa.service.UniversityImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpaApplicationTests {

    @Autowired
    UniversityImpl universityDao;

    @BeforeAll
    public void init() {

        universityDao.truncateDatabase();

        Faculty alan = new Faculty("Alan", "pass", "Alan", "Turin", "123A", true);
        Course cs1234 = new Course("CS1234", alan);
        Course cs2345 = new Course("CS2345", alan);

        Faculty ada = new Faculty("Ada", "pass", "Ada", "Lovelace", "123B", true);
        Course cs3456 = new Course("CS3456", ada);
        Course cs4567 = new Course("CS4567", ada);

        universityDao.createFaculty(alan);
        universityDao.createFaculty(ada);

        Section sec4321 = new Section("SEC4321", 50, cs1234);
        Section sec5432 = new Section("SEC5432", 50, cs1234);
        Section sec6543 = new Section("SEC6543", 50, cs2345);
        Section sec7654 = new Section("SEC7654", 50, cs3456);

        // public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship) {

        // Insert students

        Student alice = new Student("Alice", "pass", "Alice", "Wonderland", 2020, 12000);
        Student bob = new Student("Bob", "pass", "Bob", "Hope", 2021, 23000);
        Student charlie = new Student("Charlie", "pass", "Charlie", "Brown", 2019, 21000);
        Student dan = new Student("Dan", "pass", "Dan", "Craig", 2019, 0);
        Student edward = new Student("Edward", "pass", "Edward", "Scissorhands", 2022, 11000);
        Student frank = new Student("Frank", "pass", "Frank", "Herbert", 2018, 0);
        Student gregory = new Student("Gregory", "pass", "Gregory", "Park", 2023, 10000);

        universityDao.createStudent(alice);
        universityDao.createStudent(bob);
        universityDao.createStudent(charlie);
        universityDao.createStudent(dan);
        universityDao.createStudent(edward);
        universityDao.createStudent(frank);
        universityDao.createStudent(gregory);

        universityDao.createCourse(cs1234);
        universityDao.createCourse(cs2345);
        universityDao.createCourse(cs3456);
        universityDao.createCourse(cs4567);


        universityDao.createSection(sec4321);
        universityDao.createSection(sec5432);
        universityDao.createSection(sec6543);
        universityDao.createSection(sec7654);


        universityDao.enrollStudentInSection(alice, sec4321);
        universityDao.enrollStudentInSection(alice, sec5432);
        universityDao.enrollStudentInSection(bob, sec5432);
        universityDao.enrollStudentInSection(charlie, sec6543);
    }


    @Test
   public void validateNumberOfUsers() {
        List<Person> personList = universityDao.findAllUsers();
        Assert.assertEquals(9, personList.size());
        System.out.println("Number of users Test : " + personList.size() );
    }

    @Test
   public void validateNumberOfFaculty() {
        List<Faculty> personList = universityDao.findAllFaculty();
        Assert.assertEquals(2, personList.size());
        System.out.println("Number of faculty Test : " + personList.size() );
    }

    @Test
    public void validateNumberOfStudents() {
        List<Student> personList = universityDao.findAllStudents();
        Assert.assertEquals(7, personList.size());
        System.out.println("Number of students Test : " + personList.size() );
    }

    @Test
    public void validateNumberOfCourses() {
        List<Course> personList = universityDao.findAllCourses();
        Assert.assertEquals(4, personList.size());
        System.out.println("Number of Courses Test : " + personList.size() );
    }

    @Test
    public void validateNumberOfSections() {
        List<Section> personList = universityDao.findAllSections();
        Assert.assertEquals(4, personList.size());
        System.out.println("Number of Section Test : " + personList.size() );
    }


    @Test
    public void validateCourseAuthorship() {

        for(Faculty faculty: universityDao.findAllFaculty()) {
            List<Course> courses = universityDao.findCoursesForAuthor(faculty);
            assertEquals(2, courses.size());
        }

        System.out.println("Validation of number of courses" );
    }

    @Test
    public void validateSectionPerCourse() {

        for(Course course: universityDao.findAllCourses()) {
            List<Section> sections = universityDao.findSectionForCourse(course);

             if(course.getLabel().equalsIgnoreCase("CS2345")) {
                assertEquals(1, sections.size());
            }

            else if(course.getLabel().equalsIgnoreCase("CS1234")) {
                assertEquals(2, sections.size());
            }

            else if(course.getLabel().equalsIgnoreCase("CS3456")) {
                assertEquals(1, sections.size());
            }
        }

    }

    @Test
    public void validateSectionEnrollments() {

        for(Section section: universityDao.findAllSections()) {
            List<Student> students = universityDao.findStudentsInSection(section);

            if(section.getTitle().equalsIgnoreCase("SEC4321")) {
                assertEquals(1, students.size());
            }
            else if(section.getTitle().equalsIgnoreCase("SEC5432")) {
                assertEquals(2, students.size());
            }
            else if(section.getTitle().equalsIgnoreCase("SEC6543")) {
                assertEquals(1, students.size());
            }
        }
    }

    @Test
    public void validateStudentEnrollments() {

        for(Student student: universityDao.findAllStudents()) {
            List<Section> sections = universityDao.findSectionsForStudent(student);

            if(student.getFirstName().equalsIgnoreCase("Alice")) {
                assertEquals(2, sections.size());
            }
            else if(student.getFirstName().equalsIgnoreCase("Bob")) {
                assertEquals(1, sections.size());
            }
            else if(student.getFirstName().equalsIgnoreCase("Charlie")) {
                assertEquals(1, sections.size());
            }
        }
    }

    @Test
    public void validateSectionSeats() {

        for(Section section: universityDao.findAllSections()) {

            if(section.getTitle().equalsIgnoreCase("SEC4321") ||
                    section.getTitle().equalsIgnoreCase("SEC6543")) {
                assertEquals(49, section.getSeats());
            }
            else if(section.getTitle().equalsIgnoreCase("SEC5432")) {
                assertEquals(48, section.getSeats());
            }
            else if(section.getTitle().equalsIgnoreCase("SEC7654")) {
                assertEquals(50, section.getSeats());
            }
        }


    }



}
