package org.example;
import model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Person> listaPersonas = lectura("People.csv");

        for (Person per : listaPersonas) {
            System.out.println(per.toString());
        }

        //A
        //List<Person> listaPersonasMenores25 = menores25(listaPersonas);

        /*for (Person per : listaPersonasMenores25) {
            System.out.println(per.toString());
        }*/


        /* B
        List<Person> listaSinA = eliminarA(listaPersonas);
        for (Person per : listaSinA) {
            System.out.println(per.toString());
        }*/

        //C
        //primeroMadrid(listaPersonasMenores25);
        //D
        //primeroBarcelona(listaPersonasMenores25);
    }

    public static List<Person> lectura(String rutaRelativa) throws IOException {
        Path path = Paths.get(rutaRelativa).toAbsolutePath();
        List<Person> listaPersonas = new ArrayList<>();

        try {
            List<String> p = Files.readAllLines(path);
            if (!p.isEmpty()) {
                for (String s : p) {
                    String[] partes = s.split(":");

                    if (partes.length == 3) {
                        if (!partes[0].isBlank()) {
                            String nombre = partes[0];
                            int edad = Integer.parseInt(partes[2]);
                            String ciudad = partes[1];
                            if (partes[1].isBlank()) {
                                ciudad = "unknown";
                            }
                            if (partes[2].isBlank()) {
                                edad = 0;
                            }
                            listaPersonas.add(new Person(nombre, ciudad, edad));
                        } else {
                            //throw new InvalidLineFormatException("El campo nombre no puede estar vacío");
                        }
                    } else if (partes.length == 2) {
                        //Para tener en cuenta que el delimitador sea el ultimo caracter
                        if (!partes[0].isBlank() && s.charAt(s.length() - 1) == ':') {
                            String nombre = partes[0];
                            String ciudad = partes[1];
                            if (partes[1].isBlank()) {
                                ciudad = "unknown";
                            }
                            listaPersonas.add(new Person(nombre, ciudad, 0));
                        } else {
                            //throw new InvalidLineFormatException("Faltan un delimitador");
                        }
                    } else if (partes.length == 1) {
                        //throw new InvalidLineFormatException("Faltan dos delimitadores");
                    }
                }
            } else {
                System.out.println("No hay personas almacenadas en el archivo.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return listaPersonas;
    }

    public static List<Person> menores25(List<Person> personas) throws IOException {
        List<Person> menos25 = new ArrayList<>();
        for (Person p:personas) {
            if(p.getAge()<25){
                menos25.add(p);
            }
        }
        return menos25;
    }

    public static List<Person> eliminarA(List<Person> personas){
        List<Person> sinA = new ArrayList<>();
        for (Person p:personas) {
            if(p.getName().toUpperCase().charAt(0) != 'A'){
                sinA.add(p);
            }
        }
        return sinA;
    }

    public static void primeroMadrid(List<Person> personas){
        Optional<Person> personasMad = personas.stream().filter(p -> p.getTown().toUpperCase().equals("MADRID")).findFirst();
        personasMad.ifPresentOrElse(
            p -> {
                System.out.println(p.toString());
            },
            () -> System.out.println("No hay nadie de Madrid")
        );
    }

    public static void primeroBarcelona(List<Person> personas){
        Optional<Person> personasBcn = personas.stream().filter(p -> p.getTown().toUpperCase().equals("BARCELONA")).findFirst();
        personasBcn.ifPresentOrElse(
                p -> {
                    System.out.println(p.toString());
                },
                () -> System.out.println("No hay nadie de Barcelona")
        );
    }

    public static class InvalidLineFormatException extends Exception {
        public InvalidLineFormatException(String mensaje) {
            super(mensaje);
        }
    }
}