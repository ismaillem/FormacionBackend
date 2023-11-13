package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonRepository;
import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.mappers.PersonMapper;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public PersonOutputDto getPersonById(int id, String outputType) throws EntityNotFoundException {
        if(personRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado ninguna persona.");
        } else if(outputType.equals("full")){
            Person person = personRepository.findById(id).orElseThrow();
            if(studentRepository.findByPerson(person).isPresent()){
                StudentOutputDto studentOutputDto = studentRepository.findByPerson(person).orElseThrow().studentToStudentOutputDto();
                return new PersonStudentDto(person.personToPersonOutputDto(),studentOutputDto);
            }else if(profesorRepository.findByPerson(person).isPresent()){
                ProfesorOutputDto profesorOutputDto = profesorRepository.findByPerson(person).orElseThrow().profesorToProfesorOutputDto();
                return new PersonProfesorDto(person.personToPersonOutputDto(),profesorOutputDto);
            }
        }
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }

    @Override
    public Iterable<PersonOutputDto> getPersonByUsuer(String usuario, String outputType) {
        List<Person> person = personRepository.getPersonByUsuario(usuario);
        List<PersonOutputDto> personOutput = new ArrayList<>();
        for(Person p: person){
            if(studentRepository.findByPerson(p).isPresent()){
                StudentOutputDto studentOutputDto = studentRepository.findByPerson(p).orElseThrow().studentToStudentOutputDto();
                personOutput.add(new PersonStudentDto(p.personToPersonOutputDto(),studentOutputDto));
            }else if(profesorRepository.findByPerson(p).isPresent()){
                ProfesorOutputDto profesorOutputDto = profesorRepository.findByPerson(p).orElseThrow().profesorToProfesorOutputDto();
                personOutput.add(new PersonProfesorDto(p.personToPersonOutputDto(),profesorOutputDto));
            }else{
                personOutput.add(p.personToPersonOutputDto());
            }
        }
        return personOutput;
    }

    @Override
    public Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize, String outputType) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        List<PersonOutputDto> lista = personRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Person::personToPersonOutputDto).toList();
        List<PersonOutputDto> listaFinal = new ArrayList<>();
        for (PersonOutputDto p : lista) {
            if(studentRepository.findById(p.getId()).isPresent()){
                StudentOutputDto studentOutputDto = studentRepository.findById(p.getId()).orElseThrow().studentToStudentOutputDto();
                listaFinal.add(new PersonStudentDto(p,studentOutputDto));
            }else if(profesorRepository.findById(p.getId()).isPresent()){
                ProfesorOutputDto profesorOutputDto = profesorRepository.findById(p.getId()).orElseThrow().profesorToProfesorOutputDto();
                listaFinal.add(new PersonProfesorDto(p,profesorOutputDto));
            }else{
                listaFinal.add(p);
            }
        }
        return listaFinal;
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
    public void deletePersonById(int id) throws UnprocessableEntityException {
        Person p = personRepository.findById(id).orElseThrow();
        if(profesorRepository.findByPerson(p).isPresent() || studentRepository.findByPerson(p).isPresent()){
            throw new UnprocessableEntityException("Existe una persona u alumno vinculado a esta persona.");
        }else{
            personRepository.findById(id).orElseThrow();
            personRepository.deleteById(id);
        }
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

    @PersistenceContext
    private EntityManager entityManager;

    public List<PersonOutputDto> getCustomQuery(HashMap<String, Object> conditions){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);
        PageRequest pageRequest=PageRequest.of((Integer)conditions.get("pageNumber"),(Integer)conditions.get("pageSize"));
        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field,value) -> {
            switch (field){
                case "user":
                    predicates.add(cb.greaterThan(root.get(field),"%" + (String) value + "%"));
                    break;
                case "name":
                    predicates.add(cb.greaterThan(root.get(field),"%" + (String) value + "%"));
                    break;
                case "surname":
                    predicates.add(cb.greaterThan(root.get(field),"%" + (String) value + "%"));
                    break;
                case "createdAt":
                    predicates.add(cb.greaterThan(root.get(field),"%" + (String) value + "%"));
                    break;
            }
        });

        if(conditions.get("orderBy").equals("usuario")){
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).
                    orderBy(conditions.get("orderByDirection").equals("desc") ? cb.desc(root.get("usuario")) : cb.asc(root.get("usuario")));
        }else{
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).
                    orderBy(conditions.get("orderByDirection").equals("desc") ? cb.desc(root.get("name")) : cb.asc(root.get("name")));
        }


        return entityManager
                .createQuery(query).setFirstResult(pageRequest.getPageNumber() * pageRequest.getPageSize()+1).setMaxResults(pageRequest.getPageSize())
                .getResultList()
                .stream()
                .map(Person::personToPersonOutputDto)
                .toList();

    }

}