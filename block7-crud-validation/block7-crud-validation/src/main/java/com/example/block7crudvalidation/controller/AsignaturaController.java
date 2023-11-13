package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.AsignaturaServiceImpl;
import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class AsignaturaController {
    @Autowired
    AsignaturaServiceImpl asignaturaService;

    @GetMapping("/getAsig/id/{id}")
    public ResponseEntity<AsignaturaOutputDto> getAsignaturaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(asignaturaService.getAsignatura(id));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("/getAsigs")
    public Iterable<AsignaturaOutputDto> getAllAsignaturas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return asignaturaService.getAsignaturas(pageNumber, pageSize);
    }

    @PostMapping("asignatura")
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto) {
        try {
            URI location = URI.create("/asignatura");
            return ResponseEntity.created(location).body(asignaturaService.addAsignatura(asignaturaInputDto));
        }catch(Exception e){
            throw e;
        }
    }

    @PatchMapping("/asignatura/{id}")
    public AsignaturaOutputDto patchAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto, @PathVariable int id) {
        return asignaturaService.patchAsignatura(id, asignaturaInputDto);
    }

    @DeleteMapping("/asignatura/{id}")
    public ResponseEntity<String> deleteAsignaturaById(@PathVariable int id) {
        System.out.println("HOLA");
        try {
            asignaturaService.deleteAsignaturaId(id);
            return ResponseEntity.ok().body("asignatura with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("estudiantes_asignaturas/{id}")
    public Iterable<AsignaturaOutputDto> getEstudiantesAsig(@PathVariable int id){
        return asignaturaService.getEstudiantes(id);
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