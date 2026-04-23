package com.projetoTreino.controller;


import com.projetoTreino.entity.Usuario;
import com.projetoTreino.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Void> cadastrar(@RequestBody Usuario usuario){
        usuarioService.criarUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Usuario> buscarPorEmail(@PathVariable String email){
        return ResponseEntity.ok(usuarioService.findByEmail(email));
    }

    /*@PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id,@RequestBody Usuario usuario){
        usuarioService.atualizarUsuario(id,usuario);
        return ResponseEntity.noContent().build();
    }*/

}
