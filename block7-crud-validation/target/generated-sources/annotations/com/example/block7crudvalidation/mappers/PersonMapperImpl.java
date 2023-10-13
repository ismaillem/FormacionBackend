package com.example.block7crudvalidation.mappers;

import com.example.block7crudvalidation.controller.dto.PersonInputDto;
import com.example.block7crudvalidation.domain.Person;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-08T17:37:41+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public void updatePersonFromDto(PersonInputDto dto, Person entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getUsuario() != null ) {
            entity.setUsuario( dto.getUsuario() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getSurname() != null ) {
            entity.setSurname( dto.getSurname() );
        }
        if ( dto.getCompany_email() != null ) {
            entity.setCompany_email( dto.getCompany_email() );
        }
        if ( dto.getPersonal_email() != null ) {
            entity.setPersonal_email( dto.getPersonal_email() );
        }
        if ( dto.getCity() != null ) {
            entity.setCity( dto.getCity() );
        }
        entity.setActive( dto.isActive() );
        if ( dto.getCreated_date() != null ) {
            entity.setCreated_date( dto.getCreated_date() );
        }
        if ( dto.getImagen_url() != null ) {
            entity.setImagen_url( dto.getImagen_url() );
        }
        if ( dto.getTermination_date() != null ) {
            entity.setTermination_date( dto.getTermination_date() );
        }
    }
}
