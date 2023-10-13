package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class StudentPersonDto extends StudentOutputDto{
    PersonOutputDto personOutputDto;
    public StudentPersonDto(StudentOutputDto studentOutputDto, PersonOutputDto personOutputDto){
        super(studentOutputDto);
        this.personOutputDto = personOutputDto;
    }
}
