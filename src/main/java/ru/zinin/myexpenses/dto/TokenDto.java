package ru.zinin.myexpenses.dto;

import lombok.Data;

@Data
public class TokenDto {

    private String name;

    private String token;

    public TokenDto() {
    }

    public TokenDto(String name, String token) {
        this.name = name;
        this.token = token;
    }
}
