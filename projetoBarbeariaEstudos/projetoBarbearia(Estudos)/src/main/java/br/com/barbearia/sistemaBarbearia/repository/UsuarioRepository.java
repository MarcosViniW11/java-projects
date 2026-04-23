package br.com.barbearia.sistemaBarbearia.repository;

import br.com.barbearia.sistemaBarbearia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario , Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
