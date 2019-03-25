package com.khermstad.userauth.controller;

import com.khermstad.userauth.manager.NewUserManager;
import com.khermstad.userauth.to.NewUser;
import com.khermstad.userauth.to.NewUserRegistrationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private NewUserManager newUserManager;

    public RegisterController(NewUserManager newUserManager){
        this.newUserManager = newUserManager;
    }

    @PostMapping("/register")
    public ResponseEntity<NewUserRegistrationResponse> registerNewUser(@RequestBody NewUser newUser){
         return ResponseEntity.ok(newUserManager.registerNewUser(newUser));
    }

}
