package com.jiwon.blog.service;

import com.jiwon.blog.dto.request.LoginRequest;
import com.jiwon.blog.dto.response.LoginResponse;
import com.jiwon.blog.jwt.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.createJwt(authentication);
        return LoginResponse.of(token);
    }
}