package com.retomada.CadastroELoginRetomadanow.service;

import com.retomada.CadastroELoginRetomadanow.dto.AtualizarSenha;
import com.retomada.CadastroELoginRetomadanow.dto.UsuarioResponse;
import com.retomada.CadastroELoginRetomadanow.entity.Usuario;
import com.retomada.CadastroELoginRetomadanow.exception.excessao;
import com.retomada.CadastroELoginRetomadanow.repoitory.UsuarioRepository;
import com.retomada.CadastroELoginRetomadanow.util.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse me(){
        String email = SecurityUtils.getUsuarioLogado();

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()-> new excessao("Usuario não encontrado"));
        UsuarioResponse usuarioResponse = new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getRole());
        return usuarioResponse;
    }

    public void AtualizarSenha(AtualizarSenha atualizarSenha){
        String email = SecurityUtils.getUsuarioLogado();
        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(()-> new excessao("Usuario não Encontrado"));

        if(!passwordEncoder.matches(atualizarSenha.senhaAtual(), user.getSenha())){
            throw new excessao("Credenciais invalidas!");
        }

        user.setSenha(passwordEncoder.encode(atualizarSenha.novaSenha()));
        usuarioRepository.save(user);
    }

    public void DeletarMinhaConta(){
        String email = SecurityUtils.getUsuarioLogado();

        Usuario user = usuarioRepository.findByEmail(email).orElseThrow(()->new excessao("Usuario não Encontrado"));
        usuarioRepository.delete(user);
    }



}
