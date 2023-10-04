package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Repository.PersonRepository;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.controller.dto.PersonOutputDto;
import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto getPersonById(int id) throws EntityNotFoundException {
        if(personRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado ninguna persona.");
        }
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }

    @Override
    public Iterable<PersonOutputDto> getPersonByUsuer(String usuario) {
        List<Person> person = personRepository.getPersonByUsuario(usuario);
        List<PersonOutputDto> personOutput = new ArrayList<>();
        for(Person p: person){
            personOutput.add(p.personToPersonOutputDto());
        }
        return personOutput;
    }

    @Override
    public Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto addPerson(PersonInputDto person) throws UnprocessableEntityException{
        if (person.getUsuario() == null || person.getUsuario().isBlank() ||
                person.getPassword() == null || person.getPassword().isBlank() ||
                person.getName() == null || person.getName().isBlank() ||
                person.getSurname() == null || person.getSurname().isBlank() ||
                person.getCompany_email() == null || person.getCompany_email().isBlank() ||
                person.getPersonal_email() == null || person.getPersonal_email().isBlank() ||
                person.getCity() == null || person.getCity().isBlank() ||
                person.getCreated_date() == null){
            throw new UnprocessableEntityException("Algunos campos no pueden ser nulos");
        }
        if (person.getUsuario().length() > 10 || person.getUsuario().length() < 6){
            throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres ni menor a 6");
        }

        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto person,int id) throws UnprocessableEntityException {
        if(personRepository.findById(id).isEmpty()){
            throw new UnprocessableEntityException("No se ha encontrado ninguna persona.");
        }
        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto patchPerson(PersonInputDto person, int id) throws UnprocessableEntityException {
        if(personRepository.findById(id).isEmpty()) {
            throw new UnprocessableEntityException("No se ha encontrado ninguna persona.");
        }
        Person p = personRepository.findById(id).orElseThrow();
        PersonMapper.INSTANCE.updatePersonFromDto(person, p);
        return personRepository.save(p).personToPersonOutputDto();
    }


}
