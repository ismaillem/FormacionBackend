package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.domain.Fichero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FicheroServicio {
    Fichero subirFichero(String categoria, MultipartFile file) throws IOException;
    void cambiarRuta(String ruta);
    Fichero descargarFicheroId(int id);
    Fichero descargarFicheroNombre(String nombre);
}
