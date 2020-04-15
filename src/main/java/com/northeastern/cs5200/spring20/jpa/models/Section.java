package com.northeastern.cs5200.spring20.jpa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Section {

    public int getId() {
        return id;
    }

    public Section() {
        super();
        this.students = new HashSet<>();
    }

    public Section(String title, int seats, Course course) {
        super();
        this.title = title;
        this.seats = seats;
        this.course = course;
        this.students = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Enrollment> getStudents() {
        return students;
    }

    public void setStudents(Set<Enrollment> students) {
        this.students = students;
    }

    private String title;

    @OneToMany(mappedBy="section")
    private Set<Enrollment> students;

    public void addEnrollment(Enrollment enrollment) {
        this.students.add(enrollment);
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Course course;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    private int seats;
}
