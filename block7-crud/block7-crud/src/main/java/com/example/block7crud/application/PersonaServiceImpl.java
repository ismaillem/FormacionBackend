package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonaInputDto;
import com.example.block7crud.controller.dto.PersonaOutputDto;
import com.example.block7crud.domain.Persona;
import com.example.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return personaRepository.save(new Persona(persona)).PersonaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto persona) {
        Persona p = personaRepository.findById(persona.getId()).orElseThrow();
        if(persona.getNombre()!=null && !persona.getNombre().isBlank()){
            p.setNombre(persona.getNombre());
        }
        if(persona.getEdad()!=null && !persona.getEdad().isBlank()){
            p.setEdad(persona.getEdad());
        }
        if(persona.getPoblacion()!=null && !persona.getPoblacion().isBlank()){
            p.setPoblacion(persona.getPoblacion());
        }
        return personaRepository.save(p).PersonaToPersonaOutputDto();
    }

    @Override
    public void deletePersonaById(int id) {
        personaRepository.findById(id).orElseThrow();
        personaRepository.deleteById(id);
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return personaRepository.findById(id).orElseThrow().PersonaToPersonaOutputDto();
    }

    @Override
    public Iterable<PersonaOutputDto> getPersonaByName(String nombre) {
        List<Persona> personas = personaRepository.findAll();
        List<PersonaOutputDto> personasOutput = new ArrayList<>();
        for(Persona p: personas){
            if(p.getNombre().equals(nombre)){
                personasOutput.add(p.PersonaToPersonaOutputDto());
            }
        }
        return personasOutput;
    }

    @Override
    public Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::PersonaToPersonaOutputDto).toList();
    }
}


