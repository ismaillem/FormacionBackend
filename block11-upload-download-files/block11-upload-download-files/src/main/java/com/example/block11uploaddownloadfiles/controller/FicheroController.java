package com.example.block11uploaddownloadfiles.controller;

import com.example.block11uploaddownloadfiles.application.FicheroServicio;
import com.example.block11uploaddownloadfiles.domain.Fichero;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLOutput;

@RestController
public class FicheroController {
    @Autowired
    private FicheroServicio ficheroServicio;

    @PostMapping("upload")
    public Fichero subirFichero(@RequestParam("archivo")MultipartFile archivo, @RequestParam("tipo") String categoria) throws IOException {
        System.out.println("hola");
        return ficheroServicio.subirFichero(categoria, archivo);
    }

    @GetMapping("setpath")
    public void cambiarRuta(@RequestParam("path") String path) {
        ficheroServicio.cambiarRuta(path);
    }

    @GetMapping("/id/{id}")
    public Fichero descargarPorID(@PathVariable int id) {
        return ficheroServicio.descargarFicheroId(id);
    }

    @GetMapping("/nombre/{name}")
    public Fichero descargarPorNombre(@PathVariable String name) {
        return ficheroServicio.descargarFicheroNombre(name);
    }
}
