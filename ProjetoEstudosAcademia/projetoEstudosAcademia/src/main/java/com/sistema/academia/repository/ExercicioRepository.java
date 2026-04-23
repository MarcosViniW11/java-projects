package com.sistema.academia.repository;

import com.sistema.academia.model.Exercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExercicioRepository extends JpaRepository<Exercicio,Long> {
}
