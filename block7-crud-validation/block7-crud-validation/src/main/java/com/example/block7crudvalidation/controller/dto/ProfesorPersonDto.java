package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import lombok.Data;

@Data
public class ProfesorPersonDto extends ProfesorOutputDto{
    PersonOutputDto personOutputDto;
    public ProfesorPersonDto(ProfesorOutputDto profesorOutputDto, PersonOutputDto personOutputDto){
        super(profesorOutputDto);
        this.personOutputDto = personOutputDto;
    }
}