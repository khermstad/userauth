package com.khermstad.userauth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "USER")
public class User {

    @Id
    private String email;
    private String username;
    private Date dateRegistered;

    private String password;
}
