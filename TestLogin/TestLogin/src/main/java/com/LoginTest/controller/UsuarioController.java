package com.LoginTest.controller;


import com.LoginTest.dto.request.Acessar;
import com.LoginTest.dto.request.AtualizarSenha;
import com.LoginTest.dto.request.RequestDTO;
import com.LoginTest.dto.response.ResponseDTO;
import com.LoginTest.dto.response.TokenReponseDTO;
import com.LoginTest.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ResponseDTO> Cadastrar(@RequestBody RequestDTO user) {
        return ResponseEntity.ok(usuarioService.Cadastrar(user));
    }

    @PostMapping("/logar")
    public ResponseEntity<TokenReponseDTO> logar(@RequestBody RequestDTO user) {
        return ResponseEntity.ok(usuarioService.Logar(user));
    }

    @PutMapping("/atualizar-senha")
    public ResponseEntity<Void> atualizarSenha(@RequestBody AtualizarSenha dadosSenha) {
        // Pega o e-mail do usuário logado diretamente do Token validado pelo Spring Security
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();

        usuarioService.atualizarSenha(emailLogado, dadosSenha);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/me")
    public ResponseEntity<ResponseDTO> me(@RequestBody Acessar acessar) {
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(usuarioService.me(emailLogado,acessar.senha()));
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseDTO> me() {
        return ResponseEntity.ok(usuarioService.meDados());
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> meDeletar(){
        usuarioService.deletarMe();
        return ResponseEntity.noContent().build();
    }

}
