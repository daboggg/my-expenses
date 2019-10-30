package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.RequestLogin;
import ru.zinin.myexpenses.dto.RespondToken;
import ru.zinin.myexpenses.exception.PasswordWrong;
import ru.zinin.myexpenses.exception.UserNotFound;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.UserRepo;

import java.util.Optional;

@Service
public class UserService {

    TokenHolder tokenHolder;
    UserRepo userRepo;

    @Autowired
    public UserService(TokenHolder tokenHolder, UserRepo userRepo) {
        this.tokenHolder = tokenHolder;
        this.userRepo = userRepo;
    }

    public ResponseEntity<RespondToken> login(RequestLogin requestLogin) throws UserNotFound, PasswordWrong {
        User user = userRepo.getUserByEmail(requestLogin.getEmail());
        if (user == null) {
            throw new UserNotFound(requestLogin.getEmail());
        }
        if (!user.getPassword().equals(requestLogin.getPassword())) {
            throw new PasswordWrong();
        }

        RespondToken respondToken = tokenHolder.setToken(user);

        return ResponseEntity.ok(respondToken);
    }
}
