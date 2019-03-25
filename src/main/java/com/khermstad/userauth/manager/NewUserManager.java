package com.khermstad.userauth.manager;

import com.khermstad.userauth.service.UserService;
import com.khermstad.userauth.to.NewUser;
import com.khermstad.userauth.to.NewUserRegistrationResponse;
import com.khermstad.userauth.to.NewUserValidationResponse;
import org.springframework.stereotype.Component;

@Component
public class    NewUserManager {

    private UserService userService;

    public NewUserManager(UserService userService){
        this.userService = userService;
    }

    public NewUserValidationResponse validateNewUser(NewUser newUser){
        NewUserValidationResponse newUserValidationResponse = new NewUserValidationResponse();
        if (userService.usernameExists(newUser.getUsername())){
            newUserValidationResponse.setUsernameExists(true);
        }
        if(userService.emailExists(newUser.getEmail())){
            newUserValidationResponse.setEmailExists(true);
        }
        if(userService.passwordIsValid(newUser.getPassword())){
            newUserValidationResponse.setValidPassword(true);
        }
        return newUserValidationResponse;
    }

    public NewUserRegistrationResponse registerNewUser(NewUser newUser){
        NewUserValidationResponse newUserValidationResponse = validateNewUser(newUser);
        NewUserRegistrationResponse newUserRegistrationResponse = new NewUserRegistrationResponse();
        if (newUserValidationResponse.isUsernameExists() || newUserValidationResponse.isEmailExists() || !newUserValidationResponse.isValidPassword()){
            newUserRegistrationResponse.setValidationResponse(newUserValidationResponse);
            newUserRegistrationResponse.setRegistered(false);
            newUserRegistrationResponse.setUser(null);
            return newUserRegistrationResponse;
        }
        else {
            newUserRegistrationResponse.setRegistered(true);
            newUserRegistrationResponse.setValidationResponse(newUserValidationResponse);
            newUserRegistrationResponse.setUser(userService.persistNewUser(newUser));
            return newUserRegistrationResponse;
        }
    }
}
