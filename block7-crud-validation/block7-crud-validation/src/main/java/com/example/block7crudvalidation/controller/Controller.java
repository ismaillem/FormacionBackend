package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.application.PersonServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.Valid;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class Controller {
    @Autowired
    PersonServiceImpl personService;

    @GetMapping("/get/id/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/usuario/{usuario}")
    public Iterable<PersonOutputDto> getPersonByUsuario(@PathVariable String usuario){
        return personService.getPersonByUsuer(usuario);
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return personService.getAllPersons(pageNumber, pageSize);
    }

    @PostMapping()
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        try {
            URI location = URI.create("/person");
            return ResponseEntity.created(location).body(personService.addPerson(person));
        }catch(Exception e){
            throw e;
        }
    }

    @PutMapping("/put/{id}")
    public PersonOutputDto updatePerson(@Valid @RequestBody PersonInputDto person, @PathVariable int id){
        return  personService.updatePerson(person, id);
    }

    @PatchMapping("/patch/{id}")
    public PersonOutputDto patchPerson(@RequestBody PersonInputDto person, @PathVariable int id) {
        return personService.patchPerson(person, id);
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntity(UnprocessableEntityException uee) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(uee.getCe());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException enfe) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(enfe.getCe());
    }

}
