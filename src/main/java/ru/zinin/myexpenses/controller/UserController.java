package ru.zinin.myexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinin.myexpenses.component.TokenHolder;
import ru.zinin.myexpenses.dto.RequestLogin;
import ru.zinin.myexpenses.dto.RespondToken;
import ru.zinin.myexpenses.exception.PasswordWrong;
import ru.zinin.myexpenses.exception.UserNotFound;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.UserRepo;
import ru.zinin.myexpenses.service.UserService;

@RestController
public class UserController {

    @Autowired
    TokenHolder tokenHolder;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/api")
    public ResponseEntity<?> test(@RequestParam String name) throws UserNotFound {
        String asd;
        if (tokenHolder.isValidToken()) {
            asd = "ok";
        } else {
            asd = "error";
        }
        tokenHolder.updateTimeValidityToken();
        return new ResponseEntity<>(asd, HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<RespondToken> login(@RequestBody RequestLogin requestLogin) throws UserNotFound, PasswordWrong {

        return userService.login(requestLogin);
    }

    @PutMapping("/register")
    public ResponseEntity<?> register() {
        User user = new User();
        user.setEmail("vzinin@list.ru");
        user.setFirstName("vovan");
        user.setPassword("123456");
        User userDb = userRepo.save(user);
        return ResponseEntity.ok(userDb);
    }

    @PostMapping("/api/test")
    public ResponseEntity<?> test() {
//        tokenHolder.test();
        return ResponseEntity.ok("OK");
    }
}
