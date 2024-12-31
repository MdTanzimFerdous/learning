package com.spring_boot.learning.controller;

import com.spring_boot.learning.request.auth.AuthRequest;
import com.spring_boot.learning.response.ApiResponse;
import com.spring_boot.learning.services.auth.AuthService;
import com.spring_boot.learning.services.auth.AuthServiceImpl;
import com.spring_boot.learning.services.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {

    final private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody AuthRequest authRequest) {
        try {
            return  ResponseEntity.ok(new ApiResponse("User created", authService.register(authRequest)));
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Journal Not Found", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(authRequest.getEmail());
            return  ResponseEntity.ok(new ApiResponse("Login Success", token));
        }else{
            return  ResponseEntity.ok(new ApiResponse("Login Failure", "Failed"));
        }
    }
}
