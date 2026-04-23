package com.retomada.CadastroELoginRetomadanow.service;

import com.retomada.CadastroELoginRetomadanow.entity.Usuario;
import com.retomada.CadastroELoginRetomadanow.exception.excessao;
import com.retomada.CadastroELoginRetomadanow.repoitory.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UsuarioRepository usuarioRepository;

    public AdminService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(()-> new excessao("Usuario não encontrado"));
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->new excessao("Usuario não encontrado"));
    }

    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }

    public void deletar(Long id){
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()-> new excessao("Usuario não encontrado"));
        usuarioRepository.delete(usuario);
    }

}
