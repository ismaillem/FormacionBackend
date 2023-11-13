package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.domain.Fichero;
import com.example.block11uploaddownloadfiles.repository.FicheroRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class FicheroServicioImpl implements FicheroServicio{
    private String ruta;
    @Autowired
    private FicheroRepository ficheroRepository;
    @Autowired
    private ApplicationArguments args;
    @PostConstruct
    public void configurarRuta() {
        String[] argumentos = args.getSourceArgs();
        if (argumentos.length > 0) {
            this.ruta = argumentos[0];
        } else {
            // Los ficheros se guardaran en la misma carpeta en la que se encuentra el jar, en mi caso la carpeta "target".
            this.ruta = System.getProperty("user.dir");
        }
    }
    @Override
    public Fichero subirFichero(String categoria, MultipartFile file) throws IOException {
        if (!file.getOriginalFilename().endsWith(categoria)) {
            throw new IllegalArgumentException("El fichero debe tener extensión " + categoria);
        }else{
            Path filePath = Paths.get(this.ruta).resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            Fichero fichero = new Fichero();
            fichero.setNombre(file.getOriginalFilename());
            fichero.setFechaDeSubida(new Date());
            fichero.setCategoria(categoria);
            fichero = ficheroRepository.save(fichero);
            return fichero;
        }
    }

    @Override
    public void cambiarRuta(String ruta) {
        this.ruta=ruta;
    }

    @Override
    public Fichero descargarFicheroId(int id) {
        Optional<Fichero> fichero = ficheroRepository.findById(id);
        return fichero.get();
    }

    @Override
    public Fichero descargarFicheroNombre(String nombre) {
        Optional<Fichero> fichero = ficheroRepository.findByNombre(nombre);
        return fichero.get();
    }
}
