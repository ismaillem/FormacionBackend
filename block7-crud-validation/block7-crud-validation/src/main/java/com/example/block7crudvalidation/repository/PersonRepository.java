package com.example.block7crudvalidation.Repository;

import com.example.block7crudvalidation.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    public List<Person> getPersonByUsuario(String usuario);
}
