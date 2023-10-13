package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.domain.Profesor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentOutputDto {
    int id_student;
    int id_person;
    int num_hours_week;
    String coments;
    Profesor profesor;
    String branch;

    public StudentOutputDto(StudentOutputDto studentOutputDto){
        this.id_student = studentOutputDto.getId_student();
        this.id_person = studentOutputDto.getId_person();
        this.num_hours_week = studentOutputDto.getNum_hours_week();
        this.coments = studentOutputDto.getComents();
        this.profesor = studentOutputDto.getProfesor();
        this.branch = studentOutputDto.getBranch();
    }
}
