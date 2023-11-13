package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.*;
import com.example.block7crudvalidation.domain.Person;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mappers.StudentMapper;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import com.example.block7crudvalidation.repository.PersonRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public StudentOutputDto getStudent(int id, String outputType) {
        if(studentRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado ningun estudiante.");
        } else if(outputType.equals("full")){
            StudentOutputDto studentOutputDto = studentRepository.findById(id).orElseThrow().studentToStudentOutputDto();
            PersonOutputDto personOutputDto = personRepository.findById(id).orElseThrow().personToPersonOutputDto();
            return new StudentPersonDto(studentOutputDto, personOutputDto);
        }
        return studentRepository.findById(id).orElseThrow().studentToStudentOutputDto();
    }

    @Override
    public Iterable<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::studentToStudentOutputDto).toList();
    }

    @Override
    public StudentOutputDto addStudent(StudentInputDto studentInputDto) {
        Person persona = personRepository.findById(studentInputDto.getId_person()).orElseThrow();
        if(personRepository.findById(studentInputDto.getId_person()).isEmpty()){
            throw new UnprocessableEntityException("No se ha encontrado ninguna persona con ese id.");
        } else if(profesorRepository.findByPerson(persona).isPresent()){
            throw new UnprocessableEntityException("La persona no puede ser un estudiante y profesor a la vez.");
        }
        Student student = new Student(studentInputDto);
        student.setPerson(personRepository.findById(studentInputDto.getId_person()).orElseThrow());
        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto updateStudent(StudentInputDto studentInputDto, int id) {
        if(studentRepository.findById(id).isEmpty()) {
            throw new UnprocessableEntityException("No se ha encontrado ningun estudiante.");
        }
        Student student = studentRepository.findById(id).orElseThrow();
        StudentMapper.INSTANCE.updateStudentFromDto(studentInputDto, student);
        return studentRepository.save(student).studentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto addAsignatura(int id_estudiante, List<Integer> id_asignatura) {
        Student student = studentRepository.findById(id_estudiante).orElseThrow();
        for (int asig: id_asignatura) {
            if (asignaturaRepository.findById(asig).isPresent()){
                student.getAsignaturas().add(asignaturaRepository.findById(asig).orElseThrow());
            }
        }
        return student.studentToStudentOutputDto();
    }

}