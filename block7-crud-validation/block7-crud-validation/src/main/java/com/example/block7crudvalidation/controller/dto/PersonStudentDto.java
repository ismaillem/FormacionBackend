package com.example.block7crudvalidation.controller.dto;

import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.domain.Student;
import lombok.Data;

@Data
public class PersonStudentDto extends PersonOutputDto{
    StudentOutputDto studentOutputDto;
    public PersonStudentDto(PersonOutputDto personOutputDto, StudentOutputDto studentOutputDto){
        super(personOutputDto);
        this.studentOutputDto = studentOutputDto;
    }

}
