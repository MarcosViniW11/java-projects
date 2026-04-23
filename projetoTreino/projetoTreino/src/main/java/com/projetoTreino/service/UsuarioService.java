package com.projetoTreino.service;

import com.projetoTreino.entity.Usuario;
import com.projetoTreino.execao.BusinessException;
import com.projetoTreino.execao.UserNotFoundException;
import com.projetoTreino.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void criarUsuario(Usuario usuario){
        if(usuarioRepository.existsByEmail(usuario.getEmail()) == true){
            throw new BusinessException("Usuario Ja Cadastrado com email: " + usuario.getEmail());
        }
        if(usuario.getAvaliacao() != null){
            usuario.getAvaliacao().setUsuario(usuario);
        }
        usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email){
        if(usuarioRepository.existsByEmail(email) == true){
            return usuarioRepository.findByEmail(email);
        }
        throw new UserNotFoundException("Usuario com Email: "+email+ " não encontrado");
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    /*public void atualizarUsuario(Long id, Usuario usuario){
        Usuario user = usuarioRepository.findById(id).orElseThrow(()->new UserNotFoundException("Usuario não encontrado!"));

        user.setEmail(usuario.getEmail());
        user.setAvaliacao(usuario.getAvaliacao());
        user.setTreinos(usuario.getTreinos());
        usuarioRepository.save(user);
    }*/
}
