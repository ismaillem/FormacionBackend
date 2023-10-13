package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.domain.Profesor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    int id_student;
    @NotNull(message = "La persona no puede estar vacia")
    int id_person;
    @NotNull(message = "El numero de horas no puede estar vacio")
    int num_hours_week;
    String coments;
    Profesor profesor;
    @NotNull(message = "El rama principal no puede estar vacia")
    String branch;
}
