package com.example.block7crudvalidation.mappers;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Person;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel= "spring")
public interface AsignaturaMapper {
    AsignaturaMapper INSTANCE= Mappers.getMapper(AsignaturaMapper.class);
    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //void updateAsignaturaFromDto(AsignaturaInputDto dto, @MappingTarget Asignatura entity);
}