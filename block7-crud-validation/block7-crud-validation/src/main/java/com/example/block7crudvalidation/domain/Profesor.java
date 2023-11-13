package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.controller.dto.ProfesorOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue
    @Column(name="id_profesor")
    int id_profesor;
    @OneToOne
    @JoinColumn(name = "id_persona")
    Person person;
    @Column(name="coments")
    String coments;
    @Column(name="branch", nullable = false)
    String branch;

    public Profesor(ProfesorInputDto profesorInputDto){
        this.coments = profesorInputDto.getComents();
        this.branch = profesorInputDto.getBranch();
    }

    public ProfesorOutputDto profesorToProfesorOutputDto(){
        return new ProfesorOutputDto(this.id_profesor, this.person.getId(), this.coments, this.branch);
    }
}