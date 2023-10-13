package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonServiceImpl;
import com.example.block7crudvalidation.application.StudentServiceImpl;
import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("/estudiante/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id, @RequestParam(defaultValue = "simple") String outputType) {
        try {
            return ResponseEntity.ok().body(studentService.getStudent(id, outputType));
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping("estudiantes")
    public Iterable<StudentOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @PostMapping("estudiante")
    public ResponseEntity<StudentOutputDto> addStudent(@Valid @RequestBody StudentInputDto student) {
        try {
            URI location = URI.create("/estudiante");
            return ResponseEntity.created(location).body(studentService.addStudent(student));
        }catch(Exception e){
            throw e;
        }
    }

    @PatchMapping("/estudiante/{id}")
    public StudentOutputDto updateStudent(@RequestBody StudentInputDto studentInputDto, @PathVariable int id){
        studentInputDto.setId_student(id);
        return  studentService.updateStudent(studentInputDto, id);
    }

    @PatchMapping("/estudiante/{id_estudiante}/{id_asignaturas}")
    public StudentOutputDto addAsignatura(@PathVariable int id_estudiante, @RequestParam List<Integer> id_asignatura){
        return  studentService.addAsignatura(id_estudiante, id_asignatura);
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
