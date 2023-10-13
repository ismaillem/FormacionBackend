package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorOutputDto {
    int id_profesor;
    int id_person;
    String coments;
    String branch;

    public ProfesorOutputDto(ProfesorOutputDto profesorOutputDto){
        this.id_profesor = profesorOutputDto.getId_profesor();
        this.id_person = profesorOutputDto.getId_person();
        this.coments = profesorOutputDto.getComents();
        this.branch = profesorOutputDto.getBranch();
    }
}
