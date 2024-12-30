package com.spring_boot.learning.services.auth;

import com.spring_boot.learning.model.User;
import com.spring_boot.learning.request.auth.AuthRequest;

import java.util.Optional;

public interface AuthService {
    User register(AuthRequest authRequest);
    Optional<User> login(AuthRequest authRequest);

}
