package com.tekcamp.apiExercises.exceptions;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = 1629635094960524431L;

    public UserServiceException(String message) {
        super(message);
    }
}
