package ru.zinin.myexpenses.exception;

public class EmailAlreadyExist extends Exception {
    private String email;

    public EmailAlreadyExist(String email) {
        super(String.format("email already exist: '%s'",email));
    }
}
