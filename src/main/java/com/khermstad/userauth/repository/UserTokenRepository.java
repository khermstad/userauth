package com.khermstad.userauth.repository;

import com.khermstad.userauth.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken, String> {
}
