package com.example.block11uploaddownloadfiles.controller;

import com.example.block11uploaddownloadfiles.application.FicheroServicio;
import com.example.block11uploaddownloadfiles.domain.Fichero;
<<<<<<< HEAD
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
=======
>>>>>>> d19655d3aa745be19d9d0faa08b60ad5a1658f95
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

<<<<<<< HEAD
    @Operation(
            summary = "Download a file",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully downloaded file.", content = @Content(
                            mediaType = "application/octet-stream",
                            schema = @Schema(type = "string", format = "binary"))),
                    @ApiResponse(responseCode = "404", description = "Entity or file not found"),
                    @ApiResponse(responseCode = "500", description = "Error downloading file")
            }
    )
=======
>>>>>>> d19655d3aa745be19d9d0faa08b60ad5a1658f95
    @GetMapping("/id/{id}")
    public Fichero descargarPorID(@PathVariable int id) {
        return ficheroServicio.descargarFicheroId(id);
    }

    @GetMapping("/nombre/{name}")
    public Fichero descargarPorNombre(@PathVariable String name) {
        return ficheroServicio.descargarFicheroNombre(name);
    }
}
