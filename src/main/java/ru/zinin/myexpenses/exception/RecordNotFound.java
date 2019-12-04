package ru.zinin.myexpenses.exception;

public class RecordNotFound extends Exception {
    private Long id;

    public RecordNotFound(Long id) {
        super(String.format("record not found: '%s'",id));
    }
}
