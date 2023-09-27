package com.example.block6pathvariableheaders;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controlador {
    @PostMapping(value="/hola", consumes = "application/json")
    public Object devuelve(@RequestBody Object o){
        return o;
    }

    @GetMapping("/user/{id}")
    public String devuelveId(@PathVariable String id){
        return id;
    }

    @PutMapping("/put")
    public HashMap<String,String> devuelveHash(@RequestParam String var1, @RequestParam String var2){
        HashMap<String,String> hash = new HashMap<>();
        hash.put("var1",var1);
        hash.put("var2",var2);
        return hash;
    }

    @GetMapping("/header")
    public String devuelveHeader(HttpServletRequest request){
        String h1 = request.getHeader("h1");
        String h2 = request.getHeader("h2");
        return "Headers: \n" + "h1: " + h1 + "\nh2: " + h2;
    }

    @PostMapping("/all")
    public Datos devuelveTodo(@RequestBody() String body, @RequestParam Map<String, String> requestParams, HttpServletRequest request){
        List<String> headers = new ArrayList<>();
        Enumeration<String> headerNombres = request.getHeaderNames();
        while(headerNombres.hasMoreElements()){
            String headerNombre = headerNombres.nextElement();
            String headerValue = request.getHeader(headerNombre);
            headers.add(headerNombres + ": " + headerValue);
        }
        return new Datos(body, headers, new ArrayList<>(requestParams.values()));
    }

}
