package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    String id_student;
    //@OneToOne
    //@JoinColumn(name="id", nullable = false, unique = true)
    String id_persona;
    int num_hours_week;
    String coments;
    //@OneToMany
    String id_profesor;
    String branch;
}
