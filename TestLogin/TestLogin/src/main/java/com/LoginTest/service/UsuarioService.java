package com.LoginTest.service;


import com.LoginTest.dto.request.AtualizarSenha;
import com.LoginTest.dto.request.RequestDTO;
import com.LoginTest.dto.response.ResponseDTO;
import com.LoginTest.dto.response.TokenReponseDTO;
import com.LoginTest.entity.Role;
import com.LoginTest.entity.Usuario;
import com.LoginTest.infra.BusinessException;
import com.LoginTest.infra.GlobalExceptionHandler;
import com.LoginTest.infra.UserNotFoundException;
import com.LoginTest.repository.UsuarioRepository;
import com.LoginTest.tokenService.TokenService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public TokenReponseDTO Logar(RequestDTO user) {
        Usuario banco = usuarioRepository.findByEmail(user.email()).orElseThrow(() -> new UserNotFoundException("Usuario com Email: "+user.email()+" não Encontrado!"));


        if (passwordEncoder.matches(user.senha(), banco.getPassword())) {
            ResponseDTO userLogado = new ResponseDTO(banco.getId(), banco.getEmail());
            String token = tokenService.gerarToken(banco);
            return new TokenReponseDTO(token,banco.getRole().name());
        }

        throw new BusinessException("Credenciais Invalida(s)!");
    }

    public ResponseDTO Cadastrar(RequestDTO user) {
        if(!usuarioRepository.existsByEmail(user.email())) {
            Usuario usuario = new Usuario();
            usuario.setEmail(user.email());
            usuario.setPassword(passwordEncoder.encode(user.senha()));
            usuario.setRole(Role.USER);
            usuarioRepository.save(usuario);
            ResponseDTO userCadastrado = new ResponseDTO(usuario.getId(), usuario.getEmail());
            return userCadastrado;
        }
        throw new BusinessException("Usuario Já existente com esse email!");
    }

    public void atualizarSenha(String emailUsuarioLogado, AtualizarSenha dtoAtualizar) {
        // 1. Busca o usuário no banco pelo e-mail do token
        Usuario usuario = usuarioRepository.findByEmail(emailUsuarioLogado)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado!"));

        // 2. Verifica se a senha atual digitada bate com a do banco
        if (!passwordEncoder.matches(dtoAtualizar.senhaAtual(), usuario.getPassword())) {
            throw new BusinessException("A senha atual informada está incorreta!");
        }

        // 3. Encripta a nova senha e salva no banco
        usuario.setPassword(passwordEncoder.encode(dtoAtualizar.novaSenha()));
        usuarioRepository.save(usuario);
    }

    public ResponseDTO me(String email, String senha) {
        Usuario banco = usuarioRepository.findByEmail(email).orElseThrow(()->new RuntimeException("Usuario não Encontrado!"));
        if (!passwordEncoder.matches(senha, banco.getPassword())) {
            throw new BusinessException("Credenciais Invalidas!");
        }
        ResponseDTO user = new ResponseDTO(banco.getId(), banco.getEmail());
        return user;
    }

    public ResponseDTO meDados() {

        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario banco = usuarioRepository.findByEmail(emailLogado).orElseThrow(()->new RuntimeException("Usuario não Encontrado!"));
        ResponseDTO user = new ResponseDTO(banco.getId(), banco.getEmail());
        return user;
    }

    public void deletarMe(){
        String emailLogado = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario banco = usuarioRepository.findByEmail(emailLogado).orElseThrow(()->new RuntimeException("Usuario não encontrado!"));
        usuarioRepository.delete(banco);
    }



}
