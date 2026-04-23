package com.cadastroELoginEstudos.service;


import com.cadastroELoginEstudos.entity.Usuario;
import com.cadastroELoginEstudos.exception.RecursoNaoEncontradoException;
import com.cadastroELoginEstudos.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UsuarioRepository repo;

    public AdminService(UsuarioRepository usuarioRepository){
        this.repo=usuarioRepository;
    }

    public List<Usuario> listar(){
        return repo.findAll();
    }

    public Usuario buscarPorId(Long id){
        return repo.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Usuario"));
    }

    public void deletarPorId(Long id){
        Usuario usuariobuscar=buscarPorId(id);
        repo.delete(usuariobuscar);
    }

}
