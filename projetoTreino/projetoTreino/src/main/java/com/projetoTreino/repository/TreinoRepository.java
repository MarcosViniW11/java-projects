package com.projetoTreino.repository;

import com.projetoTreino.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TreinoRepository extends JpaRepository<Treino, Long> {
    Boolean existsTreinoByUsuarioId(Long usuarioId);
    Optional<List<Treino>> findByUsuarioId(Long usuarioId);
}
