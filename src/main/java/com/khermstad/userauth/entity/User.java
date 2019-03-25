package com.khermstad.userauth.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    String username;
    String email;
    String password;
    Timestamp dateRegistered;
    char active;
}
