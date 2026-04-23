package com.sistema.loginEcadastro.service;

import com.sistema.loginEcadastro.dto.request.AtualizarSenhaRequest;
import com.sistema.loginEcadastro.dto.response.UsuarioResponse;
import com.sistema.loginEcadastro.entity.Usuario;
import com.sistema.loginEcadastro.exception.CredenciaisInvalidasException;
import com.sistema.loginEcadastro.exception.RecursoNaoEncontradoException;
import com.sistema.loginEcadastro.repository.UsuarioRepository;
import com.sistema.loginEcadastro.util.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder=passwordEncoder;
    }

    public UsuarioResponse me() {
        String email = SecurityUtils.getUsuarioLogado();

        Usuario user = repo.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário"));

        return new UsuarioResponse(user.getId(), user.getEmail(), user.getRole());
    }

    public void deletarMinhaConta(){

        String email=SecurityUtils.getUsuarioLogado();

        Usuario usuario=repo.findByEmail(email).orElseThrow(
                ()->new RecursoNaoEncontradoException("Usuario"));

        repo.delete(usuario);
    }

    public void atualizarSenha(AtualizarSenhaRequest dto){
        String email= SecurityUtils.getUsuarioLogado();

        Usuario usuario=repo.findByEmail(email).orElseThrow(()->new RecursoNaoEncontradoException("usuario"));

        if(!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())){
            throw new CredenciaisInvalidasException();
        }

        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));
        repo.save(usuario);
    }

}
