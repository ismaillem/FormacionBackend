package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue
    @Column(name="id_persona")
    int id;
    @Column(name="usuario")
    String usuario;
    @Column(name="password")
    String password;
    @Column(name="name", nullable = false)
    String name;
    @Column(name="surname", nullable = false)
    String surname;
    @Column(name="company_email", nullable = false)
    String company_email;
    @Column(name="personal_email")
    String personal_email;
    @Column(name="city", nullable = false)
    String city;
    @Column(name="active", nullable = false)
    boolean active;
    @Column(name="createdAt", nullable = false)
    Date created_date;
    @Column(name="updatedAt")
    Date updatedAt;
    @Column(name="imagen_url")
    String imagen_url;
    @Column(name="termination_date")
    Date termination_date;

    public Person(PersonInputDto personInputDto){
        this.id = personInputDto.getId();
        this.usuario = personInputDto.getUsuario();
        this.password = personInputDto.getPassword();
        this.name = personInputDto.getName();
        this.surname = personInputDto.getSurname();
        this.company_email = personInputDto.getCompany_email();
        this.personal_email = personInputDto.getPersonal_email();
        this.city = personInputDto.getCity();
        this.active = personInputDto.isActive();
        this.created_date = personInputDto.getCreated_date();
        this.imagen_url = personInputDto.getImagen_url();
        this.termination_date = personInputDto.getTermination_date();
    }

    public PersonOutputDto personToPersonOutputDto(){
        return new PersonOutputDto(this.id, this.usuario, this.password, this.name,
                this.surname, this.company_email, this.personal_email, this.city,
                this.active, this.created_date, this.imagen_url, this.termination_date);
    }

}