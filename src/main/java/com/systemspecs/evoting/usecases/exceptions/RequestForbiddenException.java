package com.systemspecs.evoting.usecases.exceptions;

public class RequestForbiddenException extends RuntimeException {
    public RequestForbiddenException(String message){
        super(message);
    }
}
