package br.com.marcos.biblioteca.repository;

import br.com.marcos.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    Optional<Livro> findByTitulo(String titulo);
}
