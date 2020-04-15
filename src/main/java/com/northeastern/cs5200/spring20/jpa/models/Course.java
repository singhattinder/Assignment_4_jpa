package com.northeastern.cs5200.spring20.jpa.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Course {

    public int getId() {
        return id;
    }

    public Course(){
        super();
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Course(String label, Faculty faculty) {
        super();
        this.label = label;
        this.sections = new HashSet<>();
        this.faculty = faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne()
    private Faculty faculty;


    public void setId(int id) {
        this.id = id;
    }

    public Set<Section> getSections() {
        return sections;
    }

    public void addSection(Section section) {
        this.sections.add(section);
    }

    public void setSections(Set<Section> sections) {
        this.sections = sections;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "course")
    private Set<Section> sections;

    private String label;
}
