package com.sistema.loginEcadastro.service;

import com.sistema.loginEcadastro.config.JwtService;
import com.sistema.loginEcadastro.dto.request.LoginRequest;
import com.sistema.loginEcadastro.dto.request.RegisterRequest;
import com.sistema.loginEcadastro.dto.response.AuthResponse;
import com.sistema.loginEcadastro.entity.Role;
import com.sistema.loginEcadastro.entity.Usuario;
import com.sistema.loginEcadastro.exception.CredenciaisInvalidasException;
import com.sistema.loginEcadastro.exception.EmailJaCadastradoException;
import com.sistema.loginEcadastro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;



@Service
public class AuthService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(UsuarioRepository repo, PasswordEncoder encoder, JwtService jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void register(RegisterRequest dto) {
        if (repo.existsByEmail(dto.email())) {
            throw new EmailJaCadastradoException();
        }

        Usuario usuario = new Usuario(
                null,
                dto.email(),
                encoder.encode(dto.senha()),
                Role.USER
        );

        repo.save(usuario);
    }

    public AuthResponse login(LoginRequest dto) {
        Usuario user = repo.findByEmail(dto.email())
                .filter(u -> encoder.matches(dto.senha(), u.getSenha()))
                .orElseThrow(CredenciaisInvalidasException::new);

        String token = jwt.gerarToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }

}
