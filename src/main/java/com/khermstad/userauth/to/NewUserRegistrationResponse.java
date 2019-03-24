package com.khermstad.userauth.to;

import com.khermstad.userauth.entity.User;
import lombok.Data;

@Data
public class NewUserRegistrationResponse {
    boolean isRegistered;
    User user;
    NewUserValidationResponse validationResponse;
}
