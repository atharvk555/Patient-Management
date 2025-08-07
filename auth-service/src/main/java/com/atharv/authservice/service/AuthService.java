package com.atharv.authservice.service;


import com.atharv.authservice.dto.LoginRequestDTO;
import com.atharv.authservice.model.User;
import com.atharv.authservice.util.JWTUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Optional<String> token= userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u-> passwordEncoder.matches(loginRequestDTO.getPassword(),u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    public boolean ValidateToken(String token) {
        try{
            jwtUtil.validateToken(token);
            return true;
        } catch (JwtException e){
            return false;
        }
    }

}
