package com.northeastern.cs5200.spring20.jpa.repositories;

import com.northeastern.cs5200.spring20.jpa.models.Course;
import com.northeastern.cs5200.spring20.jpa.models.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepository extends CrudRepository<Faculty, Integer> {
}
