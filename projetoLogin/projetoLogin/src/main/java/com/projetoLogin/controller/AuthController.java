package com.projetoLogin.controller;

import com.projetoLogin.dto.LoginUsuarioDTO;
import com.projetoLogin.entity.Usuario;
import com.projetoLogin.exception.RegraNegocioException;
import com.projetoLogin.repository.UsuarioRepository;
import com.projetoLogin.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginUsuarioDTO dto){

        Usuario usuario= usuarioRepository.findByEmail(dto.getEmail()).orElseThrow(()-> new RegraNegocioException("Usuario não Encontrado!!"));

        if(!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RegraNegocioException("Senha Invalida!");
        }

        return jwtService.gerarToken(usuario);

    }



}
