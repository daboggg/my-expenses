package ru.zinin.myexpenses.exception;

public class PasswordWrong extends Exception {
    public PasswordWrong() {
        super("wrong password");
    }
}
