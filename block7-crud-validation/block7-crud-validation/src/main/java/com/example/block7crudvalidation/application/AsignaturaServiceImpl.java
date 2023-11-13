package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.AsignaturaOutputDto;
import com.example.block7crudvalidation.controller.dto.StudentOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.mappers.AsignaturaMapper;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{
    @Autowired
    AsignaturaRepository asignaturaRepository;
    @Autowired
    StudentRepository studentRepository;


    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto) {
        if (asignaturaInputDto.getInitial_date() == null || asignaturaInputDto.getFinish_date() == null){
            throw new UnprocessableEntityException("Algunos campos no pueden ser nulos");
        }
        Asignatura asig = new Asignatura(asignaturaInputDto);
        List<Student> lista = new ArrayList<Student>();
        for (Integer i:asignaturaInputDto.getStudents()) {
            lista.add(studentRepository.findById(i).orElseThrow());
        }
        asig.setStudents(lista);
        asignaturaRepository.save(asig);
        lista.forEach(student -> {
            student.getAsignaturas().add(asig);
            studentRepository.save(student);
        });
        return asig.AsignaturaToAsignaturaOutputDto();
    }

    @Override
    public void deleteAsignaturaId(int id) {
        Asignatura asig = asignaturaRepository.findById(id).orElseThrow();
        if(asig.getStudents().isEmpty()){
            asignaturaRepository.deleteById(id);
        }else{
            throw new UnprocessableEntityException("Hay alumnos cursando la asignatura.");
        }
    }

    @Override
    public AsignaturaOutputDto patchAsignatura(int id, AsignaturaInputDto asignaturaInputDto) {
        if(asignaturaRepository.findById(id).isEmpty()) {
            throw new UnprocessableEntityException("No se ha encontrado ninguna persona.");
        }
        Asignatura asig = asignaturaRepository.findById(id).orElseThrow();
        return asignaturaRepository.save(asig).AsignaturaToAsignaturaOutputDto();
    }

    @Override
    public List<AsignaturaOutputDto> getAsignaturas(int pageNumber, int pagSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pagSize);
        return asignaturaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Asignatura::AsignaturaToAsignaturaOutputDto).toList();
    }

    @Override
    public AsignaturaOutputDto getAsignatura(int id) {
        if(asignaturaRepository.findById(id).isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado ninguna asignatura.");
        }
        return asignaturaRepository.findById(id).orElseThrow().AsignaturaToAsignaturaOutputDto();
    }

    @Override
    public List<AsignaturaOutputDto> getEstudiantes(int id) {
        Student student = studentRepository.findById(id).orElseThrow();
        System.out.println(student.getAsignaturas().stream().map(Asignatura::AsignaturaToAsignaturaOutputDto).collect(Collectors.toList()));
        return student.getAsignaturas().stream().map(Asignatura::AsignaturaToAsignaturaOutputDto).collect(Collectors.toList());
    }

    @Override
    public List<AsignaturaOutputDto> getAsignaturaByIdStudent(String id) {
        return null;
    }
}