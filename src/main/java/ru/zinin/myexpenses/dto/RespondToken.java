package ru.zinin.myexpenses.dto;

import lombok.Data;

@Data
public class RespondToken {

    private String firstName;
    private String token;

    public RespondToken(String firstName, String token) {
        this.firstName = firstName;
        this.token = token;
    }
}
