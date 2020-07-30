package com.tekcamp.apiExercises.model.response;

public enum ErrorMessages {

    EMAIL_ALREADY_IN_USE("The email you entered is already in use");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
