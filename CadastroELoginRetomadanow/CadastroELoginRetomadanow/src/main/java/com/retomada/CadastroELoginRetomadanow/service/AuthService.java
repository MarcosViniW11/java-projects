package com.retomada.CadastroELoginRetomadanow.service;

import com.retomada.CadastroELoginRetomadanow.dto.AuthResponse;
import com.retomada.CadastroELoginRetomadanow.dto.CadastroRecord;
import com.retomada.CadastroELoginRetomadanow.dto.Login;
import com.retomada.CadastroELoginRetomadanow.entity.Role;
import com.retomada.CadastroELoginRetomadanow.entity.Usuario;
import com.retomada.CadastroELoginRetomadanow.exception.excessao;
import com.retomada.CadastroELoginRetomadanow.repoitory.UsuarioRepository;
import com.retomada.CadastroELoginRetomadanow.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public AuthService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder,JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Usuario CadastrarUsuario(CadastroRecord usuario) {
        if(usuarioRepository.existsByEmail(usuario.email())){
            throw new excessao("Email de Usuario ja Cadastrado");
        }

        Usuario usuarioCriado= new Usuario();
        usuarioCriado.setEmail(usuario.email());
        usuarioCriado.setSenha(passwordEncoder.encode(usuario.senha()));
        usuarioCriado.setRole(Role.USER);

        return usuarioRepository.save(usuarioCriado);
    }

    public AuthResponse Login(Login login) {
        Usuario user = usuarioRepository.findByEmail(login.email()).orElseThrow(()->new excessao("Usuario não Encontrado"));

        if(!passwordEncoder.matches(login.senha(),  user.getSenha())){
            throw new excessao("Credenciais invalidas");
        }

        String token = jwtService.gerarToken(user.getEmail(), user.getRole().name());
        return new AuthResponse(token);
    }


}
