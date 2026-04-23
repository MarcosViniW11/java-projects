package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.exception.Invalid;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UsuarioRepository usuarioRepository;

    public AdminService(UsuarioRepository usuarioRepository){
        this.usuarioRepository=usuarioRepository;
    }

    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->new Invalid("Usuario Não Encontrado"));
    }

    public void deletarPorId(Long id){
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new Invalid("Usuario não Encontrado"));
        usuarioRepository.delete(usuario);
    }

}
