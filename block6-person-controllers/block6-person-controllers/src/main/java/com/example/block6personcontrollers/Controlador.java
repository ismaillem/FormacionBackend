package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class Controlador {
    private final Persona bean1;
    private final Persona bean2;
    private final Persona bean3;

    public Controlador(@Qualifier("bean1") Persona bean1, @Qualifier("bean2") Persona bean2, @Qualifier("bean3") Persona bean3){
        this.bean1=bean1;
        this.bean2=bean2;
        this.bean3=bean3;
    }

    @GetMapping("/bean/{bean}")
    public Persona bean(@PathVariable(value="bean") String bean){
        if(bean.equals("bean1")){
            return bean1;
        }else if(bean.equals("bean2")){
            return bean2;
        }else{
            return bean3;
        }
    }
}



