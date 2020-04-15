package com.northeastern.cs5200.spring20.jpa.models;

import javax.persistence.*;

@Entity
@Table
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Enrollment() {
        super();
    }
    public Enrollment(Section section, Student student) {
        this.section = section;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    @ManyToOne()
    private Student student;

    @ManyToOne()
    private Section section;

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getFeedback() {
        return feedback;
    }

    public void setFeedback(int feedback) {
        this.feedback = feedback;
    }

    private int grade;
    private int feedback;
}
