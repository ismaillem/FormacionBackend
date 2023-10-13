package com.example.block7crudvalidation.mappers;

import com.example.block7crudvalidation.controller.dto.AsignaturaInputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-09T09:29:02+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class AsignaturaMapperImpl implements AsignaturaMapper {

    @Override
    public void updateAsignaturaFromDto(AsignaturaInputDto dto, Asignatura entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId_asignatura( dto.getId_asignatura() );
        if ( entity.getStudents() != null ) {
            List<Student> list = dto.getStudents();
            if ( list != null ) {
                entity.getStudents().clear();
                entity.getStudents().addAll( list );
            }
        }
        else {
            List<Student> list = dto.getStudents();
            if ( list != null ) {
                entity.setStudents( new ArrayList<Student>( list ) );
            }
        }
        if ( dto.getAsignatura() != null ) {
            entity.setAsignatura( dto.getAsignatura() );
        }
        if ( dto.getComents() != null ) {
            entity.setComents( dto.getComents() );
        }
        if ( dto.getInitial_date() != null ) {
            entity.setInitial_date( dto.getInitial_date() );
        }
        if ( dto.getFinish_date() != null ) {
            entity.setFinish_date( dto.getFinish_date() );
        }
    }
}
