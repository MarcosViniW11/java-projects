package com.cadastroELoginEstudos.controller;


import com.cadastroELoginEstudos.entity.Usuario;
import com.cadastroELoginEstudos.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service){
        this.service=service;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPorId(@PathVariable Long id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }


}
