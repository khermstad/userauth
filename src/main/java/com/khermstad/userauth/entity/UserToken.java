package com.khermstad.userauth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "UserToken")
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {

    @Id
    @Column(name = "username")
    String username;

    @Column(name = "token")
    String token;
}
