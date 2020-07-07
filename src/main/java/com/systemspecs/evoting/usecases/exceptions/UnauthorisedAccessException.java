package com.systemspecs.evoting.usecases.exceptions;

public class UnauthorisedAccessException extends RuntimeException {
    public UnauthorisedAccessException(String message){
        super(message);
    }
}
