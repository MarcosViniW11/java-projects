package br.com.barbearia.sistemaBarbearia.service;

import br.com.barbearia.sistemaBarbearia.entity.Usuario;
import br.com.barbearia.sistemaBarbearia.enums.Role;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario criarUsuario(String email, String senha, Role role) {

        if (usuarioRepository.existsByEmail(email)) {
            throw new RegraNegocioException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setRole(role);
        usuario.setAtivo(true);

        return usuarioRepository.save(usuario);
    }

}
