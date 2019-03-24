package com.khermstad.userauth.to;

import lombok.Data;

@Data
public class NewUser {
    String email;
    String username;
    String password;
}
