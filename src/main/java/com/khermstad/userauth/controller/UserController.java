package com.khermstad.userauth.controller;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    public UserService userService;

    public PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder){

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<String> createNewUser(
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password){

        userService.createNewUser(email, username, new Date(), password);
        return ResponseEntity.ok("User Created");
    }

    @PostMapping("/mockLogIn")
    public ResponseEntity<User> mockLogIn(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok(userService.mockLogIn(username, password));
    }

}
