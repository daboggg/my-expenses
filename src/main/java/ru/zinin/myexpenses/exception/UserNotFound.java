package ru.zinin.myexpenses.exception;

public class UserNotFound extends Exception {
    private String email;

    public UserNotFound(String email) {
        super(String.format("user not found: '%s'",email));
    }
}
