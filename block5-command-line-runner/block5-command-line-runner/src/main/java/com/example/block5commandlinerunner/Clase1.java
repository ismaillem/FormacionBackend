package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Clase1 {
    @PostConstruct
    public void muestraClase1() {
        System.out.println("Hola desde clase inicial");
    }
}
