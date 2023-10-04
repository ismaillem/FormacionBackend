package com.example.block7crudvalidation.exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class EntityNotFoundException extends RuntimeException{
    private final CustomError ce;
    public EntityNotFoundException(String message){
        super(message);
        ce = new CustomError(new Date(), 404, message);
    }

}
