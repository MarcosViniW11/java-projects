package com.projetoLogin.security;


import com.projetoLogin.entity.Usuario;
import com.projetoLogin.enums.Role;
import com.projetoLogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        if(usuarioRepository.findByEmail("admin@admin.com").isEmpty()){
            Usuario admin=new Usuario();
            admin.setEmail("admin@admin.com");
            admin.setSenha(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ROLE_ADMIN);
            usuarioRepository.save(admin);
        }
    }

}
