package com.khermstad.userauth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    long id;
    String username;
    String email;
    String password;
}