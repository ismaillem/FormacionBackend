package com.example.block7crudvalidation.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    String id_profesor;
    String id_persona;
    String coments;
    String branch;
}
