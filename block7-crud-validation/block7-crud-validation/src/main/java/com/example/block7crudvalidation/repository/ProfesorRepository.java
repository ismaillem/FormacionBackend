package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    Optional<Profesor> findByPerson(Person person);
}