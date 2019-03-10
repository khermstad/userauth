package com.khermstad.userauth.repository;

import com.khermstad.userauth.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@DisplayName("UserRepository Test Suite")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void clearRepository(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("User Repository EMPTY")
    public void testEmptyGetUser(){
        List<User> userList = userRepository.findAll();
        Assert.assertEquals(0, userList.size());
    }

    @Test
    @DisplayName("One User in User Repository")
    public void testGetOneUser(){
        userRepository.save(createUser());
        List<User> userList = userRepository.findAll();
        Assert.assertEquals(1, userList.size());
    }

    @Test
    @DisplayName("Find User By Email")
    public void findByEmail(){
        userRepository.save(createUser());
        Optional<User> user = userRepository.findByEmail("krishermstad@gmail.com");
        Assert.assertEquals("khermstad", user.get().getUsername());
    }

    public User createUser(){
        User user = new User();
        user.setEmail("krishermstad@gmail.com");
        user.setUsername("khermstad");
        user.setDateRegistered(new Date());
        return user;
    }

}