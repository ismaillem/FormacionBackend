package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.application.PersonServiceImpl;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;

@RestController
public class PersonController {

    private final ProfesorFeignClient profesorFeignClient;

    public PersonController(ProfesorFeignClient profesorFeignClient) {
        this.profesorFeignClient = profesorFeignClient;
    }
    @Autowired
    PersonServiceImpl personService;

    @GetMapping("/get/id/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {
        try {
            return ResponseEntity.ok().body(personService.getPersonById(id, outputType));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/get/usuario/{usuario}")
    public Iterable<PersonOutputDto> getPersonByUsuario(@PathVariable String usuario, @RequestParam(defaultValue = "simple") String outputType){
        return personService.getPersonByUsuer(usuario, outputType);
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize,
            @RequestParam(defaultValue = "simple") String outputType) {
        return personService.getAllPersons(pageNumber, pageSize, outputType);
    }

    @PostMapping("person")
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

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestParam int id){
        try {
            personService.deletePersonById(id);
            return ResponseEntity.ok().body("person with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/profesorfeign/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesor(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(profesorFeignClient.getProfesor(id));
        }
        catch (FeignException e){
            throw new EntityNotFoundException("No se encontró el profesor con ID: " + id);
        }
    }

    @GetMapping("customquery")
    public Iterable<PersonOutputDto> findByParam(
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") String createdAt,
            @RequestParam(required = false) String orderBy,
            @RequestParam(defaultValue = "asc", required = false) String orderByDirection,
            @RequestParam Integer pageNumber,
            @RequestParam(defaultValue = "3", required = false) Integer pageSize){

        HashMap<String, Object> data = new HashMap<>();

        if(usuario != null) data.put("usuario", usuario);
        if(name != null) data.put("name", name);
        if(surname != null) data.put("surname", surname);
        if(createdAt != null) data.put("createdAt", createdAt);
        if(orderBy != null) data.put("orderBy", orderBy);
        if(orderByDirection != null) data.put("orderByDirection", orderByDirection);
        if(pageNumber != null) data.put("pageNumber",pageNumber);
        if(pageSize != null) data.put("pageSize",pageSize);

        return personService.getCustomQuery(data);
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