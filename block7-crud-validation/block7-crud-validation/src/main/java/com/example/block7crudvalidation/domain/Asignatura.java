package com.example.block7crudvalidation.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    String id_asignatura;
    String id_student;
    String asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
}
