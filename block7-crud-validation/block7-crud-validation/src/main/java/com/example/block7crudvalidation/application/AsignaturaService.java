package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto);
    void deleteAsignaturaId(int id);
    AsignaturaOutputDto patchAsignatura(int id, AsignaturaInputDto asignaturaInputDto);
    List<AsignaturaOutputDto> getAsignaturas(int pageNumber, int pagSize);
    AsignaturaOutputDto getAsignatura(int id);
    Iterable<AsignaturaOutputDto> getEstudiantes(int id);

    List<AsignaturaOutputDto> getAsignaturaByIdStudent(String id);

}
