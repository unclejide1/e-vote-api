package com.systemspecs.evoting.usecases.exceptions;

public class FailedPreConditionException extends RuntimeException {
    public FailedPreConditionException(String message){
        super(message);
    }
}
