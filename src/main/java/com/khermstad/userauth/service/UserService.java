package com.khermstad.userauth.service;

import com.khermstad.userauth.entity.User;
import com.khermstad.userauth.entity.UserToken;
import com.khermstad.userauth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserTokenCacheService userTokenCacheService;

    public UserService(UserRepository userRepository, UserTokenCacheService userTokenCacheService, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userTokenCacheService = userTokenCacheService;
        this.passwordEncoder = passwordEncoder;
    }

    public User createNewUser(String email, String username, Date registeredDate, String password){
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setDateRegistered(registeredDate);
        newUser.setPassword(encryptPassword(password));
        return userRepository.saveAndFlush(newUser);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public String mockLogIn(String username, String password, String usersCurrentToken){
        Optional<User> user =  userRepository.findByUsername(username);

        if (user.isPresent()) {
            boolean correctPassword = passwordEncoder.matches(password, user.get().getPassword());

            if (correctPassword){
                // TODO; check for token in cache first, if not present, then log in
                Optional<UserToken> userTokenFromCache = userTokenCacheService.getUserToken(username, usersCurrentToken);
                if(userTokenFromCache.isPresent()){
                    if (userTokenFromCache.get().getToken().equals(usersCurrentToken)){
                        return "User Already Logged In";
                    }
                } else{
                    return "Wrong Token";
                }


                String token = getToken();
                System.out.println(token);
                if(null != userTokenCacheService.storeUserToken(username, token)){
                    return token;
                }
                else{
                    return "Redis Cache Down";
                }
            }
            else {
                return "Incorrect Password";
            }
        }
        return "Username Not Found";
    }

    public String encryptPassword(String input){
        return passwordEncoder.encode(input);
    }

    public String getToken(){
        return UUID.randomUUID().toString();
    }
}
