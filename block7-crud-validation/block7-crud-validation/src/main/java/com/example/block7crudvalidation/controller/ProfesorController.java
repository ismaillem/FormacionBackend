package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.ProfesorService;
import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorOutputDto;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ProfesorController {
    @Autowired
    ProfesorService profesorService;

    @PostMapping("profesor")
    public ResponseEntity<ProfesorOutputDto> addProfesor(@Valid @RequestBody ProfesorInputDto profesorInputDto) {
        try {
            URI location = URI.create("/profesor");
            return ResponseEntity.created(location).body(profesorService.addProfesor(profesorInputDto));
        }catch(Exception e){
            throw e;
        }
    }

    @GetMapping("/profesor/{id}")
    public ResponseEntity<?> getProfesorById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {
        try {
            return ResponseEntity.ok().body(profesorService.getProfesor(id, outputType));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("profesores")
    public Iterable<ProfesorOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return profesorService.getProfesores(pageNumber, pageSize);
    }

    @PatchMapping("/profesor/{id}")
    public ProfesorOutputDto updateStudent(@RequestBody ProfesorInputDto profesorInputDto, @PathVariable int id){
        profesorInputDto.setId_profesor(id);
        return  profesorService.updateProfesor(id, profesorInputDto);
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