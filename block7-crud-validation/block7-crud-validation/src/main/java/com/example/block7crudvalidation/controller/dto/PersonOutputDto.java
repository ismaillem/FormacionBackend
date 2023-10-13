package com.example.block7crudvalidation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    int id;
    String usuario;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    boolean active;
    Date created_date;
    String imagen_url;
    Date termination_date;

    public PersonOutputDto(PersonOutputDto personOutputDto){
        this.id = personOutputDto.getId();
        this.usuario = personOutputDto.getUsuario();
        this.password = personOutputDto.getPassword();
        this.name = personOutputDto.getName();
        this.surname = personOutputDto.getSurname();
        this.company_email = personOutputDto.getCompany_email();
        this.personal_email = personOutputDto.getPersonal_email();
        this.city = personOutputDto.getCity();
        this.active = personOutputDto.isActive();
        this.created_date = personOutputDto.getCreated_date();
        this.imagen_url = personOutputDto.getImagen_url();
        this.termination_date = personOutputDto.getTermination_date();
    }
}
