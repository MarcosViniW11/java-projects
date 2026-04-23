package com.cadastroELoginEstudos.service;


import com.cadastroELoginEstudos.dto.request.AtualizarSenhaRequest;
import com.cadastroELoginEstudos.dto.response.UsuarioResponse;
import com.cadastroELoginEstudos.entity.Usuario;
import com.cadastroELoginEstudos.exception.CredenciaaisInvalidasException;
import com.cadastroELoginEstudos.exception.RecursoNaoEncontradoException;
import com.cadastroELoginEstudos.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.cadastroELoginEstudos.util.SecurityUtils;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,PasswordEncoder passwordEncoder){
        this.repo=usuarioRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public UsuarioResponse me(){
        String email=SecurityUtils.getUsuarioLogado();

        Usuario usuario=repo.findByEmail(email).orElseThrow(()->new RecursoNaoEncontradoException("Usuario"));
        return new UsuarioResponse(usuario.getId(), usuario.getEmail(), usuario.getRole());
    }

    public void deletarMinhaConta(){
        String email=SecurityUtils.getUsuarioLogado();

        Usuario usuario=repo.findByEmail(email).orElseThrow(()->new RecursoNaoEncontradoException("Usuario"));
        repo.delete(usuario);
    }

    public void atualizarSenha(AtualizarSenhaRequest dto){
        String email=SecurityUtils.getUsuarioLogado();

        Usuario usuario=repo.findByEmail(email).orElseThrow(()->new RecursoNaoEncontradoException("Usuario"));

        if(!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())){
            throw new CredenciaaisInvalidasException();
        }

        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));
        repo.save(usuario);
    }

}
