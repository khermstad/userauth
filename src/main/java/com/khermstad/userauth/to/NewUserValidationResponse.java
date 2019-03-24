package com.khermstad.userauth.to;

import com.khermstad.userauth.entity.User;
import lombok.Data;

@Data
public class NewUserValidationResponse {
    boolean usernameExists = false;
    boolean emailExists = false;
    boolean validPassword = false;
}
