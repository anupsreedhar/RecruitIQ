package com.zelexon.recruitiq.controller;

import com.zelexon.recruitiq.dao.User;
import com.zelexon.recruitiq.repository.UserRepository;
import com.zelexon.recruitiq.security.AppAuthenticationProvider;
import com.zelexon.recruitiq.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AppAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        try {
            User user = authenticationProvider.authenticate(email, password);
            String token = jwtUtil.generateToken(user.getEmail(), user.getId(), user.getRole().name());
            return Map.of("token", token);
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
