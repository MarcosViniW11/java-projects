package com.example.demo.controller;

import com.example.demo.dto.request.AtualizarSenhaRequest;
import com.example.demo.dto.response.UsuarioReponse;
import com.example.demo.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioReponse> me(){
        return ResponseEntity.ok(usuarioService.me());
    }


    @PutMapping("/me/senha")
    public ResponseEntity<Void> atualizarSenha(@RequestBody AtualizarSenhaRequest dto){
        usuarioService.atualizarSenha(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> DeletarMinhaConta(){
        usuarioService.deletar();
        return ResponseEntity.ok().build();
    }

}
