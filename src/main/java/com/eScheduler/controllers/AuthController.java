package com.eScheduler.controllers;

import com.eScheduler.model.UserLogin;
import com.eScheduler.requests.LoginRequestDTO;
import com.eScheduler.responses.ResponseDTO;
import com.eScheduler.services.UserLoginService;
import com.eScheduler.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(path = "/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserLoginService userLoginService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserLoginService userLoginService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userLoginService = userLoginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO credentials) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
            UserLogin userLogin = userLoginService.getUserByCredentials(credentials.getEmail(), credentials.getPassword());
            String token = jwtUtil.generateToken(userLogin.getEmail());
            return ResponseEntity.ok(new ResponseDTO<>("User found", true, token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok(new ResponseDTO<>("Password or Email is incorrect!", false, null));
        }
    }
}
