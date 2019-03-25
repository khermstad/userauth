package com.khermstad.userauth.controller;

import com.khermstad.userauth.manager.LoginManager;
import com.khermstad.userauth.service.UserService;
import com.khermstad.userauth.to.UserLogin;
import com.khermstad.userauth.to.UserLoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    UserService userService;
    LoginManager loginManager;

    public LoginController(UserService userService, LoginManager loginManager){
        this.userService = userService;
        this.loginManager = loginManager;
    }

    @PostMapping("/login")
    public UserLoginResponse login(@RequestBody UserLogin userLogin){
        return loginManager.verifyUserLogin(userLogin);
    }

}
