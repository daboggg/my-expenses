package ru.zinin.myexpenses.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.zinin.myexpenses.dto.RespondToken;
import ru.zinin.myexpenses.dto.Token;
import ru.zinin.myexpenses.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class TokenHolder {
    @Value("${time.validity.token}")
    private long timeValidityToken;

    private TokenGenerator tokenGenerator;
    private HttpServletRequest request;

    @Autowired
    public TokenHolder(TokenGenerator tokenGenerator,HttpServletRequest request) {
        this.tokenGenerator = tokenGenerator;
        this.request = request;
    }

    private static Map<Token, Long> tokenMap = new HashMap<>();

    public RespondToken setToken(User user) {
        Token token = tokenGenerator.getToken();
        tokenMap.put(token, user.getId());
        return new RespondToken(user.getFirstName(), token.getToken());
    }

    public boolean isValidToken() {
        String tokenFromHeader = request.getHeader("Token");
        Token t = null;
        for (Token token1 : tokenMap.keySet()) {
            if (token1.getToken().equals(tokenFromHeader)) {
                t = token1;
            }
        }
        if (t == null) {
            return false;
        } else {
            if (t.getCreationTimeToken() < System.currentTimeMillis()) {
                return false;
            }
        }

        return true;
    }

    public void updateTimeValidityToken() {
        String tokenFromHeader = request.getHeader("Token");
        Token t = null;
        for (Token token : tokenMap.keySet()) {
            if (token.getToken().equals(tokenFromHeader)) {
                System.out.println("Before: "+token.getCreationTimeToken());
                token.setCreationTimeToken(System.currentTimeMillis()+timeValidityToken);
                System.out.println("After: "+token.getCreationTimeToken());
            }
        }
    }
}
