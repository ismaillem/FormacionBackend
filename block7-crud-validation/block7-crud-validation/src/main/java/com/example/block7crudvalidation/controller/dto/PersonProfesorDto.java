package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import lombok.Data;

@Data
public class PersonProfesorDto extends PersonOutputDto {
    ProfesorOutputDto profesorOutputDto;
    public PersonProfesorDto(PersonOutputDto personOutputDto, ProfesorOutputDto profesorOutputDto){
        super(personOutputDto);
        this.profesorOutputDto = profesorOutputDto;
    }
}
