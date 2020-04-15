package com.northeastern.cs5200.spring20.jpa.repositories;

import com.northeastern.cs5200.spring20.jpa.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
