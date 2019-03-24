package com.khermstad.userauth.controller;

import com.khermstad.userauth.to.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public void login(@RequestBody UserLogin userLogin){

    }

}
