package ru.zinin.myexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinin.myexpenses.dto.RequestLogin;
import ru.zinin.myexpenses.dto.RequestRegister;
import ru.zinin.myexpenses.dto.RespondToken;
import ru.zinin.myexpenses.exception.EmailAlreadyExist;
import ru.zinin.myexpenses.exception.PasswordWrong;
import ru.zinin.myexpenses.exception.UserNotFound;
import ru.zinin.myexpenses.service.UserService;

//@CrossOrigin(methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping("/api/login")
    public ResponseEntity<RespondToken> login(@RequestBody RequestLogin requestLogin) throws UserNotFound, PasswordWrong {

        return userService.login(requestLogin);
    }

    @CrossOrigin(methods = RequestMethod.PUT)
    @PutMapping("/api/register")
    public ResponseEntity<RespondToken> register(@RequestBody RequestRegister requestRegister) throws EmailAlreadyExist {
        return userService.register(requestRegister);
    }
}
