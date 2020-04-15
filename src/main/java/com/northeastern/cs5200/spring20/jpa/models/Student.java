package com.northeastern.cs5200.spring20.jpa.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Student extends Person {

    public Student() {
        super();
        this.enrollments = new HashSet<>();
    }

    public Student(String username, String password, String firstName, String lastName, int gradYear, long scholarship) {
        super(username, password, firstName, lastName);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
        this.enrollments= new HashSet<>();
    }

    private int gradYear;

    @OneToMany(mappedBy="student")
    private Set<Enrollment> enrollments;

    public void addEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    public int getGradYear() {
        return gradYear;
    }

    public void setGradYear(int gradYear) {
        this.gradYear = gradYear;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }

    private long scholarship;

}
