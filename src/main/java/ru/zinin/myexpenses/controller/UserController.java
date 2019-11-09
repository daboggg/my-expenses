package ru.zinin.myexpenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.zinin.myexpenses.dto.LoginDto;
import ru.zinin.myexpenses.dto.TokenDto;
import ru.zinin.myexpenses.exception.EmailAlreadyExist;
import ru.zinin.myexpenses.exception.PasswordWrong;
import ru.zinin.myexpenses.exception.UserNotFound;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(methods = RequestMethod.PUT)
    @PutMapping("/api/register")
    public ResponseEntity<TokenDto> register(@RequestBody User user) throws EmailAlreadyExist {
        return userService.register(user);
    }

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws UserNotFound, PasswordWrong {
        return userService.login(loginDto);
    }
}
