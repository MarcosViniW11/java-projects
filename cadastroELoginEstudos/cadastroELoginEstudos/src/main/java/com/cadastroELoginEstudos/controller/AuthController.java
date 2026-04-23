package com.cadastroELoginEstudos.controller;

import com.cadastroELoginEstudos.dto.request.LoginRequest;
import com.cadastroELoginEstudos.dto.request.RegisterRequest;
import com.cadastroELoginEstudos.dto.response.AuthResponse;
import com.cadastroELoginEstudos.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest dto){
        authService.register(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest dto){
        return ResponseEntity.ok(authService.login(dto));
    }

}
