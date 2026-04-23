package com.example.demo.service;

import com.example.demo.dto.request.AtualizarSenhaRequest;
import com.example.demo.dto.response.UsuarioReponse;
import com.example.demo.entity.Usuario;
import com.example.demo.exception.Invalid;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.util.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder){
        this.usuarioRepository=usuarioRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public UsuarioReponse me(){
        String email= SecurityUtils.getUsuarioLogado();

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()->new Invalid("Usuario não Encontrado"));
        UsuarioReponse user = new UsuarioReponse(usuario.getId(), usuario.getEmail(), usuario.getRole());
        return user;
    }

    public void atualizarSenha(AtualizarSenhaRequest dto){
        String email=SecurityUtils.getUsuarioLogado();

        Usuario usuario= usuarioRepository.findByEmail(email).orElseThrow(()->new Invalid("Usuario não Encontrado"));

        if(!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())){
            throw new Invalid("Credenciais Invalidas");
        }

        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));
        usuarioRepository.save(usuario);
    }

    public void deletar(){
        String email= SecurityUtils.getUsuarioLogado();
        Usuario usuario= usuarioRepository.findByEmail(email).orElseThrow(()->new Invalid("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
    }

}
