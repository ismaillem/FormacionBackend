package com.example.block7crudvalidation.mappers;

import com.example.block7crudvalidation.controller.dto.StudentInputDto;
import com.example.block7crudvalidation.domain.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-09T12:51:20+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public void updateStudentFromDto(StudentInputDto dto, Student entity) {
        if ( dto == null ) {
            return;
        }

        entity.setId_student( dto.getId_student() );
        entity.setNum_hours_week( dto.getNum_hours_week() );
        if ( dto.getComents() != null ) {
            entity.setComents( dto.getComents() );
        }
        if ( dto.getProfesor() != null ) {
            entity.setProfesor( dto.getProfesor() );
        }
        if ( dto.getBranch() != null ) {
            entity.setBranch( dto.getBranch() );
        }
    }
}
