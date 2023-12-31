package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;

public interface PersonService {
    PersonOutputDto getPersonById(int id, String outputType);
    Iterable<PersonOutputDto> getPersonByUsuer(String usuario, String outputType);
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize, String outputType);
    PersonOutputDto addPerson(PersonInputDto person);
    PersonOutputDto patchPerson(PersonInputDto person, int id) throws UnprocessableEntityException;
    PersonOutputDto updatePerson(PersonInputDto person, int id);
    void deletePersonById(int id);
}