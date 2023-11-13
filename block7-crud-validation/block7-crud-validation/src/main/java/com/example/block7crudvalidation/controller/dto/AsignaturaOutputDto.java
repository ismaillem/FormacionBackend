package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Student;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaOutputDto {
    int id_asignatura;
    List<Integer> students;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
}