package com.khermstad.userauth.to;

import lombok.Data;

@Data
public class UserLoginResponse {
    boolean loggedIn;
    String response;
}
