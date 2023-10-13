package com.example.block7crudvalidation.mappers;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.domain.Profesor;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel= "spring")
public interface ProfesorMapper {

    ProfesorMapper INSTANCE= Mappers.getMapper(ProfesorMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfesorFromDto(ProfesorInputDto dto, @MappingTarget Profesor entity);
}
