package com.example.block7crudvalidation.mappers;

import com.example.block7crudvalidation.controller.dto.ProfesorInputDto;
import com.example.block7crudvalidation.domain.Profesor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-09T12:51:20+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProfesorMapperImpl implements ProfesorMapper {

    @Override
    public void updateProfesorFromDto(ProfesorInputDto dto, Profesor entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId_profesor( dto.getId_profesor() );
        if ( dto.getComents() != null ) {
            entity.setComents( dto.getComents() );
        }
        if ( dto.getBranch() != null ) {
            entity.setBranch( dto.getBranch() );
        }
    }
}
