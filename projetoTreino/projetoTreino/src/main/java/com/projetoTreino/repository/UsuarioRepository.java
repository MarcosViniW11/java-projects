package com.projetoTreino.repository;

import com.projetoTreino.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsByEmail(String email);
    Usuario findByEmail(String email);
}
