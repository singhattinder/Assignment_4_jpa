package com.northeastern.cs5200.spring20.jpa.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Faculty extends Person {
    private String office;

    public Faculty() {
        super();
    }



    public Faculty(String username, String password, String firstName, String lastName, String office, Boolean tenured) {
        super(username, password, firstName, lastName);
        this.office = office;
        this.tenured = tenured;
        this.courses = new HashSet<>();

    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @OneToMany(mappedBy = "faculty")
    private Set<Course> courses;

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public boolean isTenured() {
        return tenured;
    }

    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }

    private boolean tenured;
}
