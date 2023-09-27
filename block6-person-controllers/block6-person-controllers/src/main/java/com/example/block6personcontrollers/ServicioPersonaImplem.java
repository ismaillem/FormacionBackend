package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPersonaImplem implements ServicioPersona{

    private Persona persona;
    @Override
    public Persona constuctorPersona(String nombre, String poblacion, int edad) {
        persona= new Persona(nombre,poblacion,edad);
        return persona;
    }


}
