package com.khermstad.userauth.service;

import com.khermstad.userauth.entity.UserToken;
import com.khermstad.userauth.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTokenCacheService {

    private UserTokenRepository userTokenRepository;

    public UserTokenCacheService(UserTokenRepository userTokenRepository){
        this.userTokenRepository = userTokenRepository;
    }

    public Optional<UserToken> getUserToken(String username, String token){
        Optional<UserToken> userToken = userTokenRepository.findById(username);
        if (userToken.isPresent()){
            return userToken;
        }
        return userToken;
        }


    UserToken storeUserToken(String username, String token){
        return userTokenRepository.saveAndFlush(new UserToken(username, token));
    }
}
