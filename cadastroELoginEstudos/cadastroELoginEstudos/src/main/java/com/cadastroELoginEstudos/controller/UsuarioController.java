package com.cadastroELoginEstudos.controller;

import com.cadastroELoginEstudos.dto.request.AtualizarSenhaRequest;
import com.cadastroELoginEstudos.dto.response.UsuarioResponse;
import com.cadastroELoginEstudos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> me(){
        return ResponseEntity.ok(usuarioService.me());
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarMinhaConta(){
        usuarioService.deletarMinhaConta();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/me/senha")
    public ResponseEntity<Void> atualizarSenha (@RequestBody @Valid AtualizarSenhaRequest dto){
        usuarioService.atualizarSenha(dto);
        return ResponseEntity.noContent().build();
    }

}
