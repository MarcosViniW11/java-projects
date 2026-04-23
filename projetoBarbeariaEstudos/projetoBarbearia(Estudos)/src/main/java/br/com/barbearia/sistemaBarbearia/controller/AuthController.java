package br.com.barbearia.sistemaBarbearia.controller;

import br.com.barbearia.sistemaBarbearia.dto.LoginDTO;
import br.com.barbearia.sistemaBarbearia.entity.Usuario;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.UsuarioRepository;
import br.com.barbearia.sistemaBarbearia.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(usuario);
    }

}
