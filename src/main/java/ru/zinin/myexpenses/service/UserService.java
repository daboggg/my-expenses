package ru.zinin.myexpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.LoginDto;
import ru.zinin.myexpenses.dto.TokenDto;
import ru.zinin.myexpenses.exception.EmailAlreadyExist;
import ru.zinin.myexpenses.exception.PasswordWrong;
import ru.zinin.myexpenses.exception.UserNotFound;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.UserRepo;

@Service
public class UserService {

    TokenHolder tokenHolder;
    UserRepo userRepo;

    @Autowired
    public UserService(TokenHolder tokenHolder, UserRepo userRepo) {
        this.tokenHolder = tokenHolder;
        this.userRepo = userRepo;
    }

    public ResponseEntity<TokenDto> register(User user) throws EmailAlreadyExist {
        User userExist = userRepo.getUserByEmail(user.getEmail());
        if (userExist != null) {
            throw new EmailAlreadyExist(user.getEmail());
        }

        User userFromDb = userRepo.save(user);

        TokenDto tokenDto = tokenHolder.setToken(userFromDb);

        return ResponseEntity.ok(tokenDto);
    }

    public ResponseEntity<TokenDto> login(LoginDto loginDto) throws UserNotFound, PasswordWrong {
        User user = userRepo.getUserByEmail(loginDto.getEmail());
        if (user == null) {
            throw new UserNotFound(loginDto.getEmail());
        }
        if (!user.getPassword().equals(loginDto.getPassword())) {
            throw new PasswordWrong();
        }

        TokenDto tokenDto = tokenHolder.setToken(user);

        return ResponseEntity.ok(tokenDto);
    }
}
