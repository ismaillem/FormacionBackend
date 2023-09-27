package com.example.block6personcontrollers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    String nombre;
    String poblacion;
    int edad;
}
