package com.example.block6personcontrollers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioCiudadImplem implements ServicioCiudad{
    private List<Ciudad> ciudades = new ArrayList<>();
    @Override
    public void añadirCiudad(String nombre, int numHabitantes){
        ciudades.add(new Ciudad(nombre, numHabitantes));
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return ciudades;
    }
}
