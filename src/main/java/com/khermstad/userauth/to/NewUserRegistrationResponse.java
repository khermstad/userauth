package com.khermstad.userauth.to;

import lombok.Data;

@Data
public class NewUserRegistrationResponse {
    boolean isRegistered;
    NewUser user;
    NewUserValidationResponse validationResponse;
}
