package com.spring_boot.learning.services.auth;

import com.spring_boot.learning.exceptions.UserNotFoundException;
import com.spring_boot.learning.model.Role;
import com.spring_boot.learning.model.User;
import com.spring_boot.learning.repository.AuthRepository;
import com.spring_boot.learning.request.auth.AuthRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    final private AuthRepository authRepository;
    final private BCryptPasswordEncoder passwordEncoder;
    @Override
    public User register(AuthRequest authRequest) {
        User user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role(Role.USER)
                .build();
        return authRepository.save(user);
    }

    @Override
    public Optional<User> login(AuthRequest authRequest) {
        Optional<User> user = Optional.ofNullable(authRepository.findByEmailAndPassword(authRequest.getEmail(), passwordEncoder.encode(authRequest.getPassword())).orElseThrow(() -> new UserNotFoundException("User Not Found")));
        return user;
    }
}
