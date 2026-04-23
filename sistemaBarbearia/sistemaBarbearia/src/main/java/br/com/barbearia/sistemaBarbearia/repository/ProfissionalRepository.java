package br.com.barbearia.sistemaBarbearia.repository;


import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional , Long> {
}
