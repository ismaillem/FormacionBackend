package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.stereotype.Service;


public interface PersonService {
    PersonOutputDto getPersonById(int id);
    Iterable<PersonOutputDto> getPersonByUsuer(String usuario);
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);
    PersonOutputDto addPerson(PersonInputDto person);

    PersonOutputDto patchPerson(PersonInputDto person, int id) throws UnprocessableEntityException;

    PersonOutputDto updatePerson(PersonInputDto person, int id);

}
