package com.LoginTest.controller;


import com.LoginTest.dto.response.ResponseDTO;
import com.LoginTest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioRepository repository;

    // Lista todos os usuários para o Admin
    @GetMapping("/usuarios")
    public ResponseEntity<List<ResponseDTO>> listarTodos() {
        var usuarios = repository.findAll().stream()
                .map(u -> new ResponseDTO(u.getId(), u.getEmail()))
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    // Deleta um usuário específico
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
