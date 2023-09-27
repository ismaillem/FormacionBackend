package com.example.block6personcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {
    private final ServicioPersona servPersona;
    private final ServicioCiudad servCiudad;

    public Controlador2(ServicioPersona servicioPersona, ServicioCiudad servCiudad) {
        this.servPersona = servicioPersona;
        this.servCiudad = servCiudad;
    }
    @GetMapping("/addPersona")
    public Persona persona(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion, @RequestHeader("edad") int edad){
        return servPersona.constuctorPersona(nombre,poblacion,edad*2);
    }

    @GetMapping("/getCiudad")
    public List<Ciudad> ciudades(){
        return servCiudad.obtenerCiudades();
    }
}
