package com.khermstad.userauth.manager;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.service.UserService;
import com.khermstad.userauth.to.UserLogin;
import com.khermstad.userauth.to.UserLoginResponse;
import de.mkammerer.argon2.Argon2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginManager {

    private UserService userService;
    private Argon2 argon2;

    public LoginManager(UserService userService, Argon2 argon2){
        this.userService = userService;
        this.argon2 = argon2;
    }

    public UserLoginResponse verifyUserLogin(UserLogin userLogin) {
        Optional<User> user = userService.getUser(userLogin.getUsername());
        if (user.isPresent()){
            if(user.get().getActive() == 'N'){
                return new UserLoginResponse(false, "Not Active", user.get());
            }
            if(verifyPassword(user.get().getPassword(), userLogin.getPassword())){
                return new UserLoginResponse(true, "Success", user.get());
            }
            else{
                return new UserLoginResponse(false, "invalid password", user.get());
            }
        }
        return new UserLoginResponse(false, "username not found", null);
    }

    private boolean verifyPassword(String hashedPassword, String password){
        return argon2.verify(hashedPassword, password);
    }
}
