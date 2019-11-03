package ru.zinin.myexpenses.exception;

public class InvalidToken extends Exception {
    public InvalidToken() {
        super("invalid token");
    }
}
