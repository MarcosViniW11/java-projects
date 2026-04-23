package com.sistema.loginEcadastro.controller;


import com.sistema.loginEcadastro.dto.request.AtualizarSenhaRequest;
import com.sistema.loginEcadastro.dto.response.UsuarioResponse;
import com.sistema.loginEcadastro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public UsuarioResponse meusDados() {
        return service.me();
    }

    @DeleteMapping("/me")
    public void deletarMinhaConta(){
        service.deletarMinhaConta();
    }

    @PutMapping("/me/senha")
    public ResponseEntity<Void> atualizarSenha(@RequestBody @Valid AtualizarSenhaRequest dto){
        service.atualizarSenha(dto);
        return ResponseEntity.noContent().build();
    }

}
