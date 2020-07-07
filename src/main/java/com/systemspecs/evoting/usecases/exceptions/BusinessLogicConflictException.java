package com.systemspecs.evoting.usecases.exceptions;

public class BusinessLogicConflictException extends RuntimeException {
    public BusinessLogicConflictException(String message){
        super(message);
    }
}
