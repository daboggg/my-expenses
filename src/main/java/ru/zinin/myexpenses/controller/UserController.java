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
import ru.zinin.myexpenses.model.Category;
import ru.zinin.myexpenses.model.User;
import ru.zinin.myexpenses.repo.UserRepo;
import ru.zinin.myexpenses.service.UserService;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(maxAge = 3600)
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

    @CrossOrigin(methods = RequestMethod.POST)
    @PostMapping("/api/login")
    public ResponseEntity<RespondToken> login(@RequestBody RequestLogin requestLogin) throws UserNotFound, PasswordWrong {

        return userService.login(requestLogin);
    }

    @PutMapping("/register")
    public User register() {
        User user = new User();
        user.setPassword("123456");
        user.setFirstName("VOVAN");
        user.setEmail("aa@aa.aa");
        User save = userRepo.save(user);
        return save;
    }

    @PostMapping("/api/test")
    public void test() {
//        tokenHolder.test();
        User user = new User();
        user.setPassword("123456");
        user.setFirstName("VOVAN");
        user.setEmail("KJKJKJ");


        Category category = new Category();
        category.setName("xxx");
        Category category1 = new Category();
        category1.setName("yyy");

        user.addCategory(category);
        user.addCategory(category1);
        userRepo.save(user);
    }
}
