package com.sistema.loginEcadastro.service;


import com.sistema.loginEcadastro.entity.Usuario;
import com.sistema.loginEcadastro.exception.RecursoNaoEncontradoException;
import com.sistema.loginEcadastro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UsuarioRepository repo;

    public AdminService(UsuarioRepository repo){
        this.repo=repo;
    }

    public List<Usuario> listar(){
        return repo.findAll();
    }

    public Usuario buscarPorId(Long id){
        return repo.findById(id).orElseThrow(()-> new RecursoNaoEncontradoException("usuario"));
    }

    public void deletarPorId(Long id){
        Usuario usuariodeletar = buscarPorId(id);
        repo.delete(usuariodeletar);
    }

}
