package com.northeastern.cs5200.spring20.jpa.repositories;

import com.northeastern.cs5200.spring20.jpa.models.Enrollment;
import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
}
