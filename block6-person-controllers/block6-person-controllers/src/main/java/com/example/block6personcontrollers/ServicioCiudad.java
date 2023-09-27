package com.example.block6personcontrollers;

import java.util.List;


public interface ServicioCiudad {
    public void añadirCiudad(String nombre, int numHabitantes);
    public List<Ciudad> obtenerCiudades();
}
