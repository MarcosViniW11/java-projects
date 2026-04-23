package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.entity.Role;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.Invalid;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder,JwtService jwtService){
        this.usuarioRepository=usuarioRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }

    public void register(RegisterRequest dto){
        if(usuarioRepository.existsByEmail(dto.email())){
            throw new Invalid("Email Ja Cadastrado!!");
        }

        Usuario usuario=new Usuario();
        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setRole(Role.USER);
        usuarioRepository.save(usuario);
    }

    public AuthResponse login(LoginRequest dto){
        Usuario user = usuarioRepository.findByEmail(dto.email()).filter(u-> passwordEncoder.matches(dto.senha(), u.getSenha()))
                .orElseThrow(()->new Invalid("Credenciais Invalidas!!"));

        String token= jwtService.gerarToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }

}
