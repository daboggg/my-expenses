package ru.zinin.myexpenses.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

@Data
public class Token {

    private String token;
    private long creationTimeToken;

    public Token(String token, long timeValidityToken) {
        this.token = token;
        this.creationTimeToken = System.currentTimeMillis() + timeValidityToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this.equals(o)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
