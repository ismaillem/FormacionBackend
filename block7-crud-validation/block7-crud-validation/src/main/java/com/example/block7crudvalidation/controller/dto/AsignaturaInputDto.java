package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaInputDto {
    int id_asignatura;
    List<Integer> students;
    String asignatura;
    String coments;
    @NotNull(message = "La fecha no puede estar vacia")
    Date initial_date;
    @NotNull(message = "La fecha no puede estar vacia")
    Date finish_date;
}