package com.retomada.CadastroELoginRetomadanow.controller;

import com.retomada.CadastroELoginRetomadanow.dto.AuthResponse;
import com.retomada.CadastroELoginRetomadanow.dto.CadastroRecord;
import com.retomada.CadastroELoginRetomadanow.dto.CadastroResponse;
import com.retomada.CadastroELoginRetomadanow.dto.Login;
import com.retomada.CadastroELoginRetomadanow.entity.Usuario;
import com.retomada.CadastroELoginRetomadanow.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroResponse> Cadastrar(@RequestBody CadastroRecord cadastroRecord){
        Usuario usuario = authService.CadastrarUsuario(cadastroRecord);

        CadastroResponse response = new CadastroResponse(usuario.getEmail(), usuario.getRole());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody Login usuario){
        return ResponseEntity.ok(authService.Login(usuario));
    }

}
