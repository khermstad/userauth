package com.khermstad.userauth.manager;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.service.UserService;
import com.khermstad.userauth.to.UserLogin;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginManager {

    UserService userService;

    public LoginManager(UserService userService){
        this.userService = userService;
    }

    public boolean verifyUserLogin(UserLogin userLogin) {
        Optional<User> user = userService.getUser(userLogin.getUsername());
        if (user.isPresent()){
            if(verifyPassword(user.get().getPassword(), userLogin.getPassword())){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public boolean verifyPassword(String hashedPassword, String password){
        Argon2 argon2 = Argon2Factory.create();
        return argon2.verify(hashedPassword, password);
    }
}
