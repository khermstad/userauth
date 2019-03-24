package com.khermstad.userauth.service;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.repository.UserRepository;
import com.khermstad.userauth.to.NewUser;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username){
        return userRepository.findByUsername(username);
    }

    public User persistNewUser(NewUser newUser){
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(encryptPassword(newUser.getPassword()));
        return userRepository.save(user);
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

    public String encryptPassword(String password){
        Argon2 argon2 = Argon2Factory.create();
        String hashword = argon2.hash(10, 65536, 1, password);
        return hashword;
    }

}

