package com.LoginTest.config;


import com.LoginTest.entity.Role;
import com.LoginTest.entity.Usuario;
import com.LoginTest.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o admin já existe para não criar duplicado toda vez que iniciar
        String adminEmail = "admin@admin.com";

        if (repository.findByEmail(adminEmail).isEmpty()) {
            Usuario admin = new Usuario();
            admin.setEmail(adminEmail);
            // Salva a senha "admin123" com hash
            admin.setPassword(passwordEncoder.encode("admin123"));

            // Define o cargo como ADMIN (Certifique-se que o Enum/String Role está correto)
            admin.setRole(Role.ADMIN);

            repository.save(admin);
            System.out.println("---------- ADMINISTRADOR INICIAL CRIADO ----------");
            System.out.println("Email: admin@admin.com | Senha: admin123");
            System.out.println("--------------------------------------------------");
        }
    }
}
