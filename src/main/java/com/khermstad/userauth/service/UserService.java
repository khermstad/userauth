package com.khermstad.userauth.service;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createNewUser(String email, String username, Date registeredDate, String password){
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setDateRegistered(registeredDate);
        newUser.setPassword(encryptPassword(password));
        userRepository.saveAndFlush(newUser);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User mockLogIn(String username, String password){
        Optional<User> user =  userRepository.findByUsername(username);
        boolean correctPassword = passwordEncoder.matches(password, user.get().getPassword());

        if (correctPassword){
            return user.get();
        }
        return null;
    }

    public String encryptPassword(String input){
        return passwordEncoder.encode(input);
    }


}
