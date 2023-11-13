package com.example.block7crudvalidation.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInputDto {
    int id;
    @Size(min=6, max=10, message = "El usuario no puede tener una longitud menor a 6 o superior a 10")
    String usuario;
    @NotBlank(message = "El password no puede estar vacio")
    String password;
    @NotBlank(message = "El nombre no puede estar vacio")
    String name;
    String surname;
    @NotBlank(message = "El correo no puede estar vacio")
    String company_email;
    @NotBlank(message = "El correo no puede estar vacio")
    String personal_email;
    @NotBlank(message = "La ciudad no puede estar vacia")
    String city;
    @NotNull(message = "El estado no puede estar vacio")
    boolean active;
    @NotNull(message = "La fecha no puede estar vacia")
    Date created_date;
    String imagen_url;
    Date termination_date;
}