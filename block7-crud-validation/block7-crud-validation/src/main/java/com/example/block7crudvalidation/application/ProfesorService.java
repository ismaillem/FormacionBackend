package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto);
    ProfesorOutputDto updateProfesor(int id, ProfesorInputDto profesorInputDto);
    List<ProfesorOutputDto> getProfesores(int pageNumber, int pagSize);
    ProfesorOutputDto getProfesor(int id, String outputType);

}
