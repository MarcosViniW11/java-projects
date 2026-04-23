package com.retomada.CadastroELoginRetomadanow.controller;

import com.retomada.CadastroELoginRetomadanow.dto.AtualizarSenha;
import com.retomada.CadastroELoginRetomadanow.dto.UsuarioResponse;
import com.retomada.CadastroELoginRetomadanow.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponse> getUsuario(){
        return ResponseEntity.ok(usuarioService.me());
    }

    @PutMapping("/me/senha")
    public ResponseEntity<Void> AtualizarSenha(@RequestBody AtualizarSenha atualizarSenha){
        usuarioService.AtualizarSenha(atualizarSenha);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deletarMinhaConta(){
        usuarioService.DeletarMinhaConta();
        return ResponseEntity.ok().build();
    }


}
