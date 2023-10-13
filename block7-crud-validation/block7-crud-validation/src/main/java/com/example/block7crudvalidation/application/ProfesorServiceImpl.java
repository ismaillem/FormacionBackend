package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mappers.ProfesorMapper;
import com.example.block7crudvalidation.repository.PersonRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto) {
        Person persona = personRepository.findById(profesorInputDto.getId_person()).orElseThrow();
        if(personRepository.findById(profesorInputDto.getId_person()).isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado ninguna persona con ese id.");
        } else if(studentRepository.findByPerson(persona).isPresent()){
            throw new UnprocessableEntityException("La persona no puede ser un estudiante y profesor a la vez.");
        }
        Profesor profesor = new Profesor(profesorInputDto);
        profesor.setPerson(personRepository.findById(profesorInputDto.getId_person()).orElseThrow());
        return profesorRepository.save(profesor).profesorToProfesorOutputDto();
    }

    @Override
    public ProfesorOutputDto updateProfesor(int id, ProfesorInputDto profesorInputDto) {
        if(profesorRepository.findById(id).isEmpty()) {
            throw new UnprocessableEntityException("No se ha encontrado ningun profesor.");
        }
        Profesor profesor = profesorRepository.findById(id).orElseThrow();
        ProfesorMapper.INSTANCE.updateProfesorFromDto(profesorInputDto, profesor);
        return profesorRepository.save(profesor).profesorToProfesorOutputDto();
    }

    @Override
    public List<ProfesorOutputDto> getProfesores(int pageNumber, int pagSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pagSize);
        return profesorRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Profesor::profesorToProfesorOutputDto).toList();
    }

    @Override
    public ProfesorOutputDto getProfesor(int id, String outputType) {
        if(profesorRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado ningun profesor.");
        } else if(outputType.equals("full")){
            ProfesorOutputDto profesorOutputDto = profesorRepository.findById(id).orElseThrow().profesorToProfesorOutputDto();
            PersonOutputDto personOutputDto = personRepository.findById(id).orElseThrow().personToPersonOutputDto();
            return new ProfesorPersonDto(profesorOutputDto, personOutputDto);
        }
        return profesorRepository.findById(id).orElseThrow().profesorToProfesorOutputDto();

    }

}
