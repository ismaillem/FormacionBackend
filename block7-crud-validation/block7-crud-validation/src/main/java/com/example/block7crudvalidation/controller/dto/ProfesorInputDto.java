package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorInputDto {
    int id_profesor;
    int id_person;
    String coments;
    @NotNull(message = "La materia principal no puede estar vacia")
    String branch;
}
