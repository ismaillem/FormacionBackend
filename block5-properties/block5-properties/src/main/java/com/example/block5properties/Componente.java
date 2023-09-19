package com.example.block5properties;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

@Component
public class Componente implements CommandLineRunner {
    @Value("${greeting}")
    private String greeting;

    @Value("${yaml.greeting}")
    private String greeting2;

    @Value("${my.number}")
    private int my_number;

    @Value("${yaml.my.number}")
    private int my_number2;

    @Value("${new.property:new.property no tiene valor}")
    private String new_property;

    @Value("${yaml.new.property:new.property no tiene valor}")
    private String new_property2;

    @PostConstruct
    public void muestra(){
        System.out.println("El valor de greeting es: " + greeting);
        System.out.println("El valor de greeting yml es: " + greeting2);
        System.out.println("El valor de my.number es: " + my_number);
        System.out.println("El valor de my.number yml es: " + my_number2);
        System.out.println("El valor de new.property es: " + new_property);
        System.out.println("El valor de new.property yml es: " + new_property2);
    }

    @Override
    public void run(String... args) throws Exception{
        String myUrl =System.getenv("MYURL");
        String myUrl2 = "NO_tengo_valor";
        System.out.println("El valor de myurl es: " + myUrl);
        if(System.getenv("MYURL2")!=null){
            myUrl2 =System.getenv("MYURL2");
            System.out.println("El valor de MYURL2 es: " + myUrl2);
        }else{
            System.out.println("El valor de MYURL2 es: " + myUrl2);
        }


    }

}
