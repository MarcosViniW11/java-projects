package com.projetoLogin.service;

import com.projetoLogin.entity.Usuario;
import com.projetoLogin.enums.Role;
import com.projetoLogin.exception.RegraNegocioException;
import com.projetoLogin.repository.UsuarioRepository;
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

    public Usuario criarUsuario(String email, String senha, Role role){

        if(usuarioRepository.existsByEmail(email)){
            throw new RegraNegocioException("email ja cadastrado!!");
        }

        Usuario usuario=new Usuario();

        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setRole(role);
        usuario.setAtivo(true);

        return  usuarioRepository.save(usuario);

    }

    public Usuario atualizarUsuario(Long id, String novoEmail, String novaSenha) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));

        // Atualizar email (se mudou)
        if (novoEmail != null && !novoEmail.equals(usuario.getEmail())) {

            if (usuarioRepository.existsByEmail(novoEmail)) {
                throw new RegraNegocioException("Email já cadastrado");
            }

            usuario.setEmail(novoEmail);
        }

        // Atualizar senha (se enviada)
        if (novaSenha != null && !novaSenha.isBlank()) {
            usuario.setSenha(passwordEncoder.encode(novaSenha));
        }

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }

}
