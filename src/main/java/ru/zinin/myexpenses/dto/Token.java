package ru.zinin.myexpenses.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class Token {

    private String token;
    private long creationTimeToken;

    public Token(String token, long timeValidityToken) {
        this.token = token;
        this.creationTimeToken = System.currentTimeMillis() + timeValidityToken;
    }
}
