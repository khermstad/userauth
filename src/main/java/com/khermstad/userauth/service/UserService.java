package com.khermstad.userauth.service;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean usernameExists(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean emailExists(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean passwordIsValid(String password){
        if (password.length() < 6){
            return false;
        }
        return true;
    }

}

