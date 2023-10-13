package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    @Column(name="id_asignatura")
    int id_asignatura;
    @ManyToMany
    @JoinColumn(name = "id_student")
    List<Student> students;
    @Column(name="asignatura")
    String asignatura;
    @Column(name="coment")
    String coments;
    @Column(name="initialDate", nullable = false)
    Date initial_date;
    @Column(name="finishDate", nullable = false)
    Date finish_date;


    public Asignatura(AsignaturaInputDto asignaturaInputDto){
        this.asignatura = asignaturaInputDto.getAsignatura();
        this.coments = asignaturaInputDto.getComents();
        this.initial_date = asignaturaInputDto.getInitial_date();
        this.finish_date = asignaturaInputDto.getFinish_date();
    }

    public AsignaturaOutputDto AsignaturaToAsignaturaOutputDto(){
        List<Integer> list = this.students.stream()
            .map(Student::getId_student)
            .collect(Collectors.toList());
        return new AsignaturaOutputDto(this.id_asignatura, list, this.asignatura, this.coments,
                this.initial_date, this.finish_date);
    }

}
