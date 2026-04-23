package com.cadastroELoginEstudos.service;

import com.cadastroELoginEstudos.dto.request.LoginRequest;
import com.cadastroELoginEstudos.dto.request.RegisterRequest;
import com.cadastroELoginEstudos.dto.response.AuthResponse;
import com.cadastroELoginEstudos.entity.Role;
import com.cadastroELoginEstudos.entity.Usuario;
import com.cadastroELoginEstudos.exception.CredenciaaisInvalidasException;
import com.cadastroELoginEstudos.exception.EmailJaCadastradoException;
import com.cadastroELoginEstudos.repository.UsuarioRepository;
import com.cadastroELoginEstudos.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder,JwtService jwtService){
        this.usuarioRepository=usuarioRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }

    public void register(RegisterRequest dto){
        if(usuarioRepository.existsByEmail(dto.email())){
            throw new EmailJaCadastradoException();
        }

        Usuario usuario=new Usuario(
                null,
                dto.email(),
                passwordEncoder.encode(dto.senha()),
                Role.USER
        );

        usuarioRepository.save(usuario);

    }

    public AuthResponse login(LoginRequest dto){
        Usuario user = usuarioRepository.findByEmail(dto.email())
                        .filter(u-> passwordEncoder.matches(dto.senha(), u.getSenha()))
                        .orElseThrow((CredenciaaisInvalidasException::new));

        String token= jwtService.gerarToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);

    }

}
