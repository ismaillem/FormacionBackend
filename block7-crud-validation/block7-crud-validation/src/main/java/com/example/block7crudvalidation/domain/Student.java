package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    @Column(name="id_student")
    int id_student;
    @OneToOne
    @JoinColumn(name="id_persona")
    Person person;
    @Column(name="num_houes_week", nullable = false)
    int num_hours_week;
    @Column(name="coments")
    String coments;
    @ManyToOne
    @JoinColumn(name="id_profesor")
    Profesor profesor;
    @Column(name="branch", nullable = false)
    String branch;
    @ManyToMany
    @JoinColumn(name="id_asignatura")
    List<Asignatura> asignaturas;

    public Student(StudentInputDto studentInputDto){
        this.num_hours_week = studentInputDto.getNum_hours_week();
        this.coments = studentInputDto.getComents();
        this.branch = studentInputDto.getBranch();
    }

    public StudentOutputDto studentToStudentOutputDto(){
        return new StudentOutputDto(this.id_student, this.person.getId(), this.num_hours_week, this.coments,
                this.profesor, this.branch);
    }
}