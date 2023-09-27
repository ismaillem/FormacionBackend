package com.example.block6personcontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {
    private final ServicioPersona servPersona;
    private final ServicioCiudad servCiudad;

    public Controlador1(ServicioPersona servPersona,ServicioCiudad servCiudad) {
        this.servPersona = servPersona;
        this.servCiudad = servCiudad;
    }
    @GetMapping("/addPersona")
    public Persona persona(@RequestHeader("nombre") String nombre, @RequestHeader("poblacion") String poblacion, @RequestHeader("edad") int edad){
        return servPersona.constuctorPersona(nombre,poblacion,edad);
    }

    @PostMapping(value= "/addCiudad", consumes= "application/json")
    public ResponseEntity<Ciudad> añadirCiudad(@RequestBody Ciudad c){
        servCiudad.añadirCiudad(c.getNombre(),c.getNumeroHabitantes() );
        return ResponseEntity.ok(c);
    }


}
