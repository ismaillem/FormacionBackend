package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonaInputDto;
import com.example.block7crud.controller.dto.PersonaOutputDto;


public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto persona);

    PersonaOutputDto updatePersona(PersonaInputDto persona);

    void deletePersonaById( int id);

    PersonaOutputDto getPersonaById(int id);

    Iterable<PersonaOutputDto> getPersonaByName(String nombre);

    Iterable<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);

}
