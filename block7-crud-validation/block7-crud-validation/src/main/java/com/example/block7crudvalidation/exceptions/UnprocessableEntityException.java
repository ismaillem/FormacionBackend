package com.example.block7crudvalidation.exceptions;

import lombok.Getter;

import java.util.Date;

@Getter
public class UnprocessableEntityException extends RuntimeException{
    private final CustomError ce;
    public UnprocessableEntityException(String message){
        super(message);
        ce = new CustomError(new Date(), 422, message);
    }

}
