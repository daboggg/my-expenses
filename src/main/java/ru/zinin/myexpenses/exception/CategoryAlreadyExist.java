package ru.zinin.myexpenses.exception;

public class CategoryAlreadyExist extends Exception {
    private String category;

    public CategoryAlreadyExist(String category) {
        super(String.format("categoty already exist: '%s'",category));
    }
}
