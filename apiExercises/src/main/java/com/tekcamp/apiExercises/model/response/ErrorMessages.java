package com.tekcamp.apiExercises.model.response;

public enum ErrorMessages {

    EMAIL_ALREADY_IN_USE ("The email you entered is already in use"),
    REQUIRED_FIELD_MISSING ("Missing one or more required fields. Required fields include: First Name, Last Name, Email, and Password"),
    EMPTY_USER_LIST("There are no users in the database"),
    USERID_DOES_NOT_EXIST("There is no user in the database with that userId"),
    USER_EMAIL_DOES_NOT_EXIST("There is no user in the database with that email"),
    MISSING_USERID("Must input user ID to update user information"),
    MISSING_USER_PASSWORD("Must input user password to update user information"),
    PASSWORD_INCORRECT("User password entered is incorrect"),
    NOTHING_TO_UPDATE("No updated user information has been entered"),
    USER_DELETED("The user has been deleted");

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
