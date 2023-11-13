package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;

import java.util.List;

public interface StudentService {
    StudentOutputDto getStudent(int id, String outputType);
    Iterable<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDto addStudent(StudentInputDto studentInputDto);
    StudentOutputDto updateStudent(StudentInputDto studentInputDto, int id);
    StudentOutputDto addAsignatura(int id_estudiante, List<Integer> id_asignatura);

}